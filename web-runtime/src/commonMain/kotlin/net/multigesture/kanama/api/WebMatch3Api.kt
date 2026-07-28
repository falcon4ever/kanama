@file:OptIn(net.multigesture.kanama.backend.InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import kotlin.math.ln
import kotlin.math.pow
import kotlin.coroutines.resume
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.cancel
import kotlinx.coroutines.suspendCancellableCoroutine
import net.multigesture.kanama.backend.GodotHandle as BackendGodotHandle
import net.multigesture.kanama.backend.AudioStreamPlayerBackendContractProbe
import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.InitialGodotCallDescriptors
import net.multigesture.kanama.backend.InputActionBackendContractProbe
import net.multigesture.kanama.backend.InputBackendContractProbe
import net.multigesture.kanama.backend.GodotObjectBackendContractProbe
import net.multigesture.kanama.backend.InputEventBackendContractProbe
import net.multigesture.kanama.backend.InputEventMouseButtonBackendContractProbe
import net.multigesture.kanama.backend.PackedSceneBackendContractProbe
import net.multigesture.kanama.backend.SignalBackendContractProbe
import net.multigesture.kanama.backend.ViewportBackendContractProbe
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.GodotColor
import net.multigesture.kanama.backend.GPUParticles2DBackendContractProbe
import net.multigesture.kanama.backend.PropertyTweenerBackendContractProbe
import net.multigesture.kanama.backend.ResourceLoaderBackendContractProbe
import net.multigesture.kanama.backend.SceneTreeBackendContractProbe
import net.multigesture.kanama.backend.TweenBackendContractProbe
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.web.webScriptInstance
import net.multigesture.kanama.web.WebObjectId

/**
 * Compile-visible Match3 wrapper slice for Task 57d.
 *
 * Each gameplay operation that has not entered the typed Web call manifest fails explicitly and is
 * harvested into the generated Web coverage backlog. Task 57e replaces these markers one call
 * family at a time; none of them silently returns a placeholder value at runtime.
 */
@PublishedApi
internal fun unsupportedWebGameplayCall(signature: String): Nothing =
  error("Kanama Web gameplay call is not implemented: $signature (Task 57e backlog)")

@PublishedApi
internal fun unsupportedWebGameplayFamily(signature: String): Nothing =
  error("Kanama Web gameplay call family is not implemented: $signature")

internal object WebSignalCallbackRegistry {
  private data class Entry(
    val ownerHandle: Int,
    val sourceHandle: Int,
    val oneShot: Boolean,
    val callback: (() -> Unit)? = null,
    val objectCallback: ((Int) -> Unit)? = null,
  )

  private var nextId = 1
  private val entries = mutableMapOf<Int, Entry>()

  val size: Int
    get() = entries.size

  fun register(
    ownerHandle: Int,
    sourceHandle: Int,
    oneShot: Boolean,
    callback: () -> Unit,
  ): Int {
    check(nextId > 0) { "Kanama Web signal callback registry exhausted" }
    val id = nextId++
    entries[id] = Entry(ownerHandle, sourceHandle, oneShot, callback = callback)
    return id
  }

  fun registerObject(
    ownerHandle: Int,
    sourceHandle: Int,
    oneShot: Boolean,
    callback: (Int) -> Unit,
  ): Int {
    check(nextId > 0) { "Kanama Web signal callback registry exhausted" }
    val id = nextId++
    entries[id] = Entry(ownerHandle, sourceHandle, oneShot, objectCallback = callback)
    return id
  }

  fun unregister(id: Int) {
    entries.remove(id)
  }

  fun releaseOwner(ownerHandle: Int) {
    entries.entries.removeAll { it.value.ownerHandle == ownerHandle }
  }

  fun releaseSource(sourceHandle: Int) {
    entries.entries.removeAll { it.value.sourceHandle == sourceHandle }
  }

  fun dispatch(ownerHandle: Int, id: Int) {
    val entry = requireEntry(ownerHandle, id)
    val callback =
      entry.callback ?: error("Kanama Web signal callback id=$id expects an emitted object")
    if (entry.oneShot) entries.remove(id)
    callback()
  }

  fun dispatchObject(ownerHandle: Int, id: Int, argHandle: Int) {
    val entry = requireEntry(ownerHandle, id)
    val callback =
      entry.objectCallback
        ?: error("Kanama Web signal callback id=$id does not accept an emitted object")
    if (entry.oneShot) entries.remove(id)
    callback(argHandle)
  }

  private fun requireEntry(ownerHandle: Int, id: Int): Entry {
    val entry = entries[id] ?: error("Stale Kanama Web signal callback id=$id")
    check(entry.ownerHandle == ownerHandle) {
      "Kanama Web signal callback id=$id belongs to handle=${entry.ownerHandle}, not $ownerHandle"
    }
    return entry
  }
}

object Mathf {
  val PI: Double = kotlin.math.PI
  val TAU: Double = kotlin.math.PI * 2.0

  fun abs(value: Double): Double = kotlin.math.abs(value)

  fun log(value: Double): Double = ln(value)

  fun cos(value: Double): Double = kotlin.math.cos(value)

  fun sin(value: Double): Double = kotlin.math.sin(value)

  fun clamp(value: Double, min: Double, max: Double): Double = value.coerceIn(min, max)

  fun lerp(from: Double, to: Double, weight: Double): Double = from + (to - from) * weight

  fun inverseLerp(from: Double, to: Double, value: Double): Double =
    if (to == from) 0.0 else (value - from) / (to - from)

  fun sqrt(value: Double): Double = kotlin.math.sqrt(value)

  fun pow(value: Double, exponent: Double): Double = value.pow(exponent)

  fun atan2(y: Double, x: Double): Double = kotlin.math.atan2(y, x)

  fun min(a: Double, b: Double): Double = kotlin.math.min(a, b)

  fun min(a: Long, b: Long): Long = kotlin.math.min(a, b)

  fun max(a: Double, b: Double): Double = kotlin.math.max(a, b)

  /** Godot's roundi: round half away from zero to the nearest integer. */
  fun roundToInt(value: Double): Long = kotlin.math.round(value).toLong()

  /** Godot's wrapi: wrap [value] into the half-open range [min, max). */
  fun wrap(value: Long, min: Long, max: Long): Long {
    val range = max - min
    if (range == 0L) return min
    return min + ((value - min) % range + range) % range
  }

  fun isEqualApprox(a: Double, b: Double): Boolean = kotlin.math.abs(a - b) < 1e-6

  /** Godot's angle_lerp / lerp_angle: interpolate the shortest arc between two angles (radians). */
  fun lerpAngle(from: Double, to: Double, weight: Double): Double {
    val difference = (to - from) % TAU
    val distance = (2.0 * difference) % TAU - difference
    return from + distance * weight
  }
}

class GodotSignal internal constructor(private val owner: GodotObject, private val name: String) {
  fun connect(target: GodotObject, method: String, flags: Long = 0L): Long =
    SignalBackendContractProbe(owner.backendHandle)
      .connect(target.backendHandle, name, method, flags)

  fun connect(
    target: GodotObject,
    argumentCount: Int = 0,
    flags: Long = 0L,
    callback: () -> Unit,
  ): Long {
    require(argumentCount in 0..1) {
      "Kanama Web signal lambda callbacks currently support at most one emitted argument"
    }
    val callbackId =
      WebSignalCallbackRegistry.register(
        target.handle.value,
        owner.handle.value,
        oneShot = flags and GodotObject.CONNECT_ONE_SHOT != 0L,
        callback,
      )
    val dispatchMethod =
      if (argumentCount == 0) "_kanama_web_signal_dispatch0" else "_kanama_web_signal_dispatch1"
    val result =
      SignalBackendContractProbe(owner.backendHandle)
        .connectBound(target.backendHandle, name, dispatchMethod, callbackId.toLong(), flags)
    if (result != 0L) WebSignalCallbackRegistry.unregister(callbackId)
    return result
  }

  /**
   * Connects a one-argument object signal (e.g. body_entered). The emitted Godot object arrives
   * wrapped as [GodotObject]; resolve a Kanama script via kotlinScriptInstance or re-type it with a
   * wrapper constructor.
   */
  fun connectObject(
    target: GodotObject,
    flags: Long = 0L,
    callback: (GodotObject) -> Unit,
  ): SignalConnection? {
    val callbackId =
      WebSignalCallbackRegistry.registerObject(
        target.handle.value,
        owner.handle.value,
        oneShot = flags and GodotObject.CONNECT_ONE_SHOT != 0L,
      ) { argHandle ->
        callback(GodotObject(WebObjectId(argHandle)))
      }
    val result =
      SignalBackendContractProbe(owner.backendHandle)
        .connectBound(
          target.backendHandle,
          name,
          "_kanama_web_signal_dispatch_object",
          callbackId.toLong(),
          flags,
        )
    if (result != 0L) {
      WebSignalCallbackRegistry.unregister(callbackId)
      return null
    }
    return SignalConnection(owner, name, target, callbackId)
  }

  /** Suspends until this signal fires once (a one-shot connection resumes the coroutine). */
  suspend fun await(target: GodotObject, argumentCount: Int = 0) {
    require(argumentCount in 0..1) {
      "Kanama Web signal await currently supports at most one emitted argument"
    }
    suspendCancellableCoroutine { continuation ->
      connect(target, argumentCount, GodotObject.CONNECT_ONE_SHOT) {
        if (continuation.isActive) continuation.resume(Unit)
      }
    }
  }
}

/** A live bound connection; [close] disconnects and releases the Kotlin callback. */
class SignalConnection
internal constructor(
  private val source: GodotObject,
  private val name: String,
  private val target: GodotObject,
  private val callbackId: Int,
) {
  private var closed = false

  fun close() {
    if (closed) return
    closed = true
    GodotBackendCalls.invokeStringNameBoundCallableLongRetLong(
      InitialGodotCallDescriptors.OBJECT_DISCONNECT,
      source.backendHandle,
      name,
      target.backendHandle,
      "_kanama_web_signal_dispatch_object",
      callbackId.toLong(),
      -1L,
    )
    WebSignalCallbackRegistry.unregister(callbackId)
  }
}

inline fun <reified T : Any> GodotObject.kotlinScriptInstance(): T? =
  webScriptInstance(handle.value) as? T

class Area2D(godotObject: GodotHandle) : Node2D(godotObject)

class Viewport(godotObject: GodotHandle) : Node(godotObject.toBackendHandle()) {
  /** The active 3D camera as a tracked handle. */
  fun getCamera3D(): Camera3D? =
    GodotBackendCalls.invokeNoArgsRetHandle(
        InitialGodotCallDescriptors.VIEWPORT_GET_CAMERA_3D,
        backendHandle,
      )
      ?.let { Camera3D(WebObjectId(it.backendToken().toInt())) }

  fun getVisibleRect(): Rect2 =
    ViewportBackendContractProbe(backendHandle).visibleRect.let { rect ->
      Rect2(
        Vector2(rect.position.x.toDouble(), rect.position.y.toDouble()),
        Vector2(rect.size.x.toDouble(), rect.size.y.toDouble()),
      )
    }

  /**
   * Scaling/anti-aliasing enum values (tps's graphics menu records them in its config file). The
   * Web canvas fixes its own scaling, so writing them back through the Window facade is inert.
   */
  companion object {
    const val SCALING_3D_MODE_BILINEAR: Long = 0L
    const val SCALING_3D_MODE_FSR: Long = 1L
    const val SCALING_3D_MODE_FSR2: Long = 2L
    const val SCALING_3D_MODE_METALFX_SPATIAL: Long = 3L
    const val SCALING_3D_MODE_METALFX_TEMPORAL: Long = 4L
    const val MSAA_DISABLED: Long = 0L
    const val MSAA_2X: Long = 1L
    const val MSAA_4X: Long = 2L
    const val MSAA_8X: Long = 3L
    const val SCREEN_SPACE_AA_DISABLED: Long = 0L
    const val SCREEN_SPACE_AA_FXAA: Long = 1L
  }
}

class GPUParticles2D(godotObject: GodotHandle) : Node2D(godotObject) {
  var emitting: Boolean
    get() = GPUParticles2DBackendContractProbe(backendHandle).emitting
    set(value) {
      GPUParticles2DBackendContractProbe(backendHandle).emitting = value
    }

  val lifetime: Double
    get() = GPUParticles2DBackendContractProbe(backendHandle).lifetime
}

class AudioStreamPlayer(godotObject: GodotHandle) : Node(godotObject.toBackendHandle()) {
  fun setVolumeDb(value: Double) {
    AudioStreamPlayerBackendContractProbe(backendHandle).setVolumeDb(value)
  }

  fun setBus(value: String) {
    AudioStreamPlayerBackendContractProbe(backendHandle).setBus(value)
  }

  fun setStreamFromPath(path: String) {
    val stream =
      ResourceLoaderBackendContractProbe.load(path, "AudioStream", ResourceLoader.CACHE_MODE_REUSE)
        ?: return
    try {
      AudioStreamPlayerBackendContractProbe(backendHandle).setStream(stream)
    } finally {
      releaseWebResource(stream.backendToken().toInt())
    }
  }

  fun setPitchScale(value: Double) {
    AudioStreamPlayerBackendContractProbe(backendHandle).setPitchScale(value)
  }

  fun setStreamPaused(paused: Boolean) {
    GodotBackendCalls.invokeBoolArg(
      InitialGodotCallDescriptors.AUDIOSTREAMPLAYER_SET_STREAM_PAUSED,
      backendHandle,
      paused,
    )
  }

  fun play(fromPosition: Double = 0.0) {
    AudioStreamPlayerBackendContractProbe(backendHandle).play(fromPosition)
  }

  fun stop() {
    AudioStreamPlayerBackendContractProbe(backendHandle).stop()
  }

  object Signals {
    const val finished = "finished"
  }

  companion object {
    fun create(): AudioStreamPlayer {
      val handle =
        checkNotNull(AudioStreamPlayerBackendContractProbe.create()) {
          "Godot could not instantiate AudioStreamPlayer"
        }
      return AudioStreamPlayer(WebObjectId(handle.backendToken().toInt()))
    }
  }
}

class PackedScene internal constructor(backendHandle: BackendGodotHandle) : Resource(backendHandle) {
  constructor(godotObject: GodotHandle) : this(godotObject.toBackendHandle())

  /** Harness-grade release of the scene's browser handle (already-released is an error). */
  @ManualGodotLifetimeApi
  fun close() {
    releaseWebResource(handle.value)
  }

  fun instantiate(): GodotObject? =
    PackedSceneBackendContractProbe(backendHandle).instantiate()?.let(::GodotObject)
}

class Tween internal constructor(backendHandle: BackendGodotHandle) : GodotObject(backendHandle) {
  object Signals {
    const val finished = "finished"
  }

  fun kill() {
    // Desktop parity: kill() on a tween that already finished (its handles released by the
    // finished-signal cleanup) is a legal no-op — the FPS clears its weapon-swap tween on
    // every swap and at exit_tree.
    if (!isWebBrowserHandleLive(handle.value)) return
    TweenBackendContractProbe(backendHandle).kill()
    WebSignalCallbackRegistry.releaseSource(handle.value)
  }

  fun setParallel(parallel: Boolean = true): Tween {
    val returned = TweenBackendContractProbe(backendHandle).setParallel(parallel)
    return if (returned == null || returned.backendToken() == backendHandle.backendToken()) {
      this
    } else {
      Tween(returned)
    }
  }

  fun tweenProperty(
    target: GodotObject,
    property: String,
    finalValue: Any?,
    duration: Double,
  ): PropertyTweener? =
    when (finalValue) {
      is Vector2 ->
        TweenBackendContractProbe(backendHandle)
          .tweenProperty(
            target.backendHandle,
            property,
            GodotVector2(finalValue.x.toFloat(), finalValue.y.toFloat()),
            duration,
          )
          ?.let(::PropertyTweener)
      is Color ->
        TweenBackendContractProbe(backendHandle)
          .tweenProperty(
            target.backendHandle,
            property,
            GodotColor(finalValue.r, finalValue.g, finalValue.b, finalValue.a),
            duration,
          )
          ?.let(::PropertyTweener)
      else ->
        unsupportedWebGameplayCall(
          "Tween.tween_property final value ${finalValue?.let { it::class.simpleName } ?: "null"}"
        )
    }

  fun bindNode(node: Node): Tween {
    val returned = TweenBackendContractProbe(backendHandle).bindNode(node.backendHandle)
    return wrapSelf(returned)
  }

  fun setEase(ease: Long): Tween {
    val returned = TweenBackendContractProbe(backendHandle).setEase(ease)
    return wrapSelf(returned)
  }

  fun tweenProperty(
    target: GodotObject,
    property: String,
    finalValue: net.multigesture.kanama.types.Vector3,
    duration: Double,
  ): PropertyTweener? =
    TweenBackendContractProbe(backendHandle)
      .tweenProperty(
        target.backendHandle,
        property,
        net.multigesture.kanama.backend.GodotVector3(
          finalValue.x.toFloat(),
          finalValue.y.toFloat(),
          finalValue.z.toFloat(),
        ),
        duration,
      )
      ?.let(::PropertyTweener)

  /** Chain a callback step to a registered method on a Kanama script (FPS change_weapon). */
  fun tweenCallback(target: GodotObject, method: String) {
    TweenBackendContractProbe(backendHandle).tweenCallback(target.backendHandle, method)
  }

  /** Tween a registered method with an interpolated double (the Callable binds proxy-side). */
  fun tweenMethod(target: GodotObject, method: String, from: Double, to: Double, duration: Double) {
    GodotBackendCalls.invokeCallableDoubleRangeRetHandle(
      InitialGodotCallDescriptors.TWEEN_TWEEN_METHOD,
      backendHandle,
      target.backendHandle,
      method,
      from,
      to,
      duration,
    )
  }

  private fun wrapSelf(returned: net.multigesture.kanama.backend.GodotHandle?): Tween {
    check(returned != null && returned.backendToken() == backendHandle.backendToken()) {
      "Tween fluent call did not return its receiver"
    }
    return this
  }

  companion object {
    const val TRANS_BACK = 10L
    const val TRANS_ELASTIC = 6L
    const val EASE_OUT = 1L
    const val EASE_OUT_IN = 3L
  }
}

class PropertyTweener internal constructor(backendHandle: BackendGodotHandle) :
  GodotObject(backendHandle) {
  fun setTrans(transition: Long): PropertyTweener =
    wrapOrThis(PropertyTweenerBackendContractProbe(backendHandle).setTrans(transition))

  fun setEase(ease: Long): PropertyTweener =
    wrapOrThis(PropertyTweenerBackendContractProbe(backendHandle).setEase(ease))

  private fun wrapOrThis(value: BackendGodotHandle?): PropertyTweener =
    if (value == null || value.backendToken() == backendHandle.backendToken()) this
    else PropertyTweener(value)
}

class InputEventMouseButton private constructor(backendHandle: BackendGodotHandle) :
  GodotObject(backendHandle) {
  fun getButtonIndex(): Long =
    InputEventMouseButtonBackendContractProbe(backendHandle).getButtonIndex()

  fun isPressed(): Boolean = InputEventBackendContractProbe(backendHandle).isPressed()

  fun isReleased(): Boolean = InputEventBackendContractProbe(backendHandle).isReleased()

  companion object {
    const val MOUSE_BUTTON_LEFT = 1L

    fun from(event: GodotObject): InputEventMouseButton? =
      event
        .takeIf {
          GodotObjectBackendContractProbe(it.backendHandle).isClass("InputEventMouseButton")
        }
        ?.let { InputEventMouseButton(it.backendHandle) }
  }
}

object Input {
  fun setCustomMouseCursor(texture: Texture2D?, hotspot: Vector2 = Vector2.ZERO) {
    InputBackendContractProbe.setCustomMouseCursor(
      texture?.requireOpenHandle(),
      hotspot = GodotVector2(hotspot.x.toFloat(), hotspot.y.toFloat()),
    )
  }

  fun isActionPressed(action: String): Boolean =
    InputBackendContractProbe.isActionPressed(action)

  fun isActionJustPressed(action: String): Boolean =
    InputBackendContractProbe.isActionJustPressed(action)

  fun actionPress(action: String) {
    InputActionBackendContractProbe.actionPress(action)
  }

  fun actionRelease(action: String) {
    InputActionBackendContractProbe.actionRelease(action)
  }

  const val MOUSE_MODE_VISIBLE = 0L
  const val MOUSE_MODE_CAPTURED = 2L

  fun setMouseMode(mode: Long) {
    InputActionBackendContractProbe.setMouseMode(mode)
  }

  fun getMouseMode(): Long = InputActionBackendContractProbe.getMouseMode()

  /**
   * Composed from two get_axis reads (deadzone-normalized identically for digital keys, the
   * only inputs the Web demos drive), clamped to unit length like Godot's get_vector.
   */
  fun getVector(
    negativeX: String,
    positiveX: String,
    negativeY: String,
    positiveY: String,
    deadzone: Double = -1.0,
  ): Vector2 {
    val vector = Vector2(getAxis(negativeX, positiveX), getAxis(negativeY, positiveY))
    val length = vector.length()
    if (deadzone >= 0.0) {
      // Godot's deadzone remap: inside the deadzone reads zero, outside rescales to [0, 1].
      if (length <= deadzone) return Vector2.ZERO
      if (length > 1.0) return vector / length
      return vector * ((length - deadzone) / (1.0 - deadzone) / length)
    }
    return if (length > 1.0) vector / length else vector
  }

  /** exact_match baked false; x1000 integer transport. */
  fun getActionRawStrength(action: String): Double =
    GodotBackendCalls.invokeStringNameRetDoubleSingleton(
      InitialGodotCallDescriptors.INPUT_GET_ACTION_RAW_STRENGTH,
      action,
    )

  fun getAxis(negativeAction: String, positiveAction: String): Double =
    GodotBackendCalls.invokeStringNameStringNameRetDoubleSingleton(
      InitialGodotCallDescriptors.INPUT_GET_AXIS,
      negativeAction,
      positiveAction,
    )
}

class SceneTree internal constructor(backendHandle: BackendGodotHandle) : GodotObject(backendHandle) {
  fun quit(exitCode: Long = 0L) {
    SceneTreeBackendContractProbe(backendHandle).quit(exitCode)
  }

  fun callGroup(group: String, method: String) {
    SceneTreeBackendContractProbe(backendHandle).callGroup(group, method)
  }

  fun setPaused(paused: Boolean) {
    GodotBackendCalls.invokeBoolArg(
      InitialGodotCallDescriptors.SCENETREE_SET_PAUSE,
      backendHandle,
      paused,
    )
  }

  fun reloadCurrentScene() {
    GodotBackendCalls.invokeNoArgsRetLong(
      InitialGodotCallDescriptors.SCENETREE_RELOAD_CURRENT_SCENE,
      backendHandle,
    )
  }

  /** Instance form of [Companion.delaySeconds] for `getTree().delaySeconds(...)` call sites. */
  suspend fun delaySeconds(seconds: Double) = SceneTree.delaySeconds(seconds)

  companion object {
    suspend fun delaySeconds(seconds: Double) {
      require(seconds.isFinite() && seconds >= 0.0) {
        "SceneTree.delaySeconds requires a finite, non-negative duration"
      }
      suspendCancellableCoroutine { continuation ->
        val taskId =
          WebFrameScheduler.scheduleDelay(seconds, continuation.context[Job]) {
            if (continuation.isActive) continuation.resume(Unit)
          }
        continuation.invokeOnCancellation { WebFrameScheduler.cancelTask(taskId) }
      }
    }
  }
}

internal object WebFrameCoroutineDispatcher : CoroutineDispatcher() {
  val pendingCount: Int
    get() = WebFrameScheduler.pendingCount

  override fun dispatch(context: kotlin.coroutines.CoroutineContext, block: Runnable) {
    WebFrameScheduler.dispatch(
      context[Job],
      block,
      scopeOwnerHandle = context[WebScopeOwner]?.ownerHandle ?: 0,
    )
  }
}

/**
 * Carries the script that owns a [KanamaScope] through the coroutine context.
 *
 * Without it, a coroutine's owner is whoever happened to be on the stack at `launch`, which is
 * routinely the wrong script: one script calling another's method (`Main.game_over` ->
 * `hud.showGameOver()`) launches on the callee's scope from the caller's callback.
 */
internal class WebScopeOwner(val ownerHandle: Int) :
  kotlin.coroutines.AbstractCoroutineContextElement(Key) {
  companion object Key : kotlin.coroutines.CoroutineContext.Key<WebScopeOwner>
}

/**
 * A script's coroutine scope.
 *
 * The owner defaults to the script being constructed: script instances are built inside their own
 * owner scope, so `override val kanamaScope = KanamaScope()` binds to the right script with no
 * ceremony at the call site. A scope built outside any script callback (handle 0) keeps the old
 * behaviour and is attributed to the ambient callback at dispatch time.
 */
class KanamaScope(private val ownerHandle: Int = WebFrameScheduler.currentOwnerOrZero()) :
  CoroutineScope {
  private val job = SupervisorJob()
  override val coroutineContext =
    WebFrameCoroutineDispatcher + job + WebScopeOwner(ownerHandle)

  fun cancel() {
    job.cancel()
  }
}

interface KanamaCoroutineOwner {
  val kanamaScope: KanamaScope
}

object MainThread {
  fun post(block: () -> Unit) {
    WebFrameScheduler.post(block)
  }
}

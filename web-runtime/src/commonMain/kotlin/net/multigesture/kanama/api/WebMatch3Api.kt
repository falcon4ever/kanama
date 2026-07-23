@file:OptIn(net.multigesture.kanama.backend.InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import kotlin.math.ln
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
    val callback: () -> Unit,
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
    entries[id] = Entry(ownerHandle, sourceHandle, oneShot, callback)
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
    val entry = entries[id] ?: error("Stale Kanama Web signal callback id=$id")
    check(entry.ownerHandle == ownerHandle) {
      "Kanama Web signal callback id=$id belongs to handle=${entry.ownerHandle}, not $ownerHandle"
    }
    if (entry.oneShot) entries.remove(id)
    entry.callback()
  }
}

object Mathf {
  fun abs(value: Double): Double = kotlin.math.abs(value)

  fun log(value: Double): Double = ln(value)
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
    require(argumentCount == 0) {
      "Kanama Web signal lambda callbacks currently support zero emitted arguments"
    }
    val callbackId =
      WebSignalCallbackRegistry.register(
        target.handle.value,
        owner.handle.value,
        oneShot = flags and GodotObject.CONNECT_ONE_SHOT != 0L,
        callback,
      )
    val result =
      SignalBackendContractProbe(owner.backendHandle)
        .connectBound(
          target.backendHandle,
          name,
          "_kanama_web_signal_dispatch0",
          callbackId.toLong(),
          flags,
        )
    if (result != 0L) WebSignalCallbackRegistry.unregister(callbackId)
    return result
  }
}

inline fun <reified T : Any> GodotObject.kotlinScriptInstance(): T? =
  webScriptInstance(handle.value) as? T

class Area2D(godotObject: GodotHandle) : Node2D(godotObject)

class Viewport(godotObject: GodotHandle) : Node(godotObject.toBackendHandle()) {
  fun getVisibleRect(): Rect2 =
    ViewportBackendContractProbe(backendHandle).visibleRect.let { rect ->
      Rect2(
        Vector2(rect.position.x.toDouble(), rect.position.y.toDouble()),
        Vector2(rect.size.x.toDouble(), rect.size.y.toDouble()),
      )
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

  fun play(fromPosition: Double = 0.0) {
    AudioStreamPlayerBackendContractProbe(backendHandle).play(fromPosition)
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
  fun instantiate(): GodotObject? =
    PackedSceneBackendContractProbe(backendHandle).instantiate()?.let(::GodotObject)
}

class Tween internal constructor(backendHandle: BackendGodotHandle) : GodotObject(backendHandle) {
  object Signals {
    const val finished = "finished"
  }

  fun kill() {
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

  companion object {
    const val TRANS_BACK = 10L
    const val TRANS_ELASTIC = 6L
    const val EASE_OUT = 1L
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
}

class SceneTree internal constructor(backendHandle: BackendGodotHandle) : GodotObject(backendHandle) {
  fun quit(exitCode: Long = 0L) {
    SceneTreeBackendContractProbe(backendHandle).quit(exitCode)
  }

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
    WebFrameScheduler.dispatch(context[Job], block)
  }
}

class KanamaScope : CoroutineScope {
  private val job = SupervisorJob()
  override val coroutineContext = WebFrameCoroutineDispatcher + job

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

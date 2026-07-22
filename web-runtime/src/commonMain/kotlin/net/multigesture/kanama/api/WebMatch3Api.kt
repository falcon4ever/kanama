@file:OptIn(net.multigesture.kanama.backend.InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import kotlin.math.ln
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.cancel
import net.multigesture.kanama.backend.GodotHandle as BackendGodotHandle
import net.multigesture.kanama.backend.InputBackendContractProbe
import net.multigesture.kanama.backend.PackedSceneBackendContractProbe
import net.multigesture.kanama.backend.SignalBackendContractProbe
import net.multigesture.kanama.backend.ViewportBackendContractProbe
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.web.webScriptInstance

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
  ): Long = unsupportedWebGameplayCall("Signal.connect_callable")
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
    get() = unsupportedWebGameplayCall("GPUParticles2D.is_emitting")
    set(value) {
      unsupportedWebGameplayCall("GPUParticles2D.set_emitting")
    }

  val lifetime: Double
    get() = unsupportedWebGameplayCall("GPUParticles2D.get_lifetime")
}

class AudioStreamPlayer(godotObject: GodotHandle) : Node(godotObject.toBackendHandle()) {
  fun setVolumeDb(value: Double) {
    unsupportedWebGameplayCall("AudioStreamPlayer.set_volume_db")
  }

  fun setBus(value: String) {
    unsupportedWebGameplayCall("AudioStreamPlayer.set_bus")
  }

  fun setStreamFromPath(path: String) {
    unsupportedWebGameplayCall("AudioStreamPlayer.set_stream_from_path")
  }

  fun setPitchScale(value: Double) {
    unsupportedWebGameplayCall("AudioStreamPlayer.set_pitch_scale")
  }

  fun play(fromPosition: Double = 0.0) {
    unsupportedWebGameplayCall("AudioStreamPlayer.play")
  }

  companion object {
    fun create(): AudioStreamPlayer =
      unsupportedWebGameplayCall("ClassDB.instantiate_AudioStreamPlayer")
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
    unsupportedWebGameplayCall("Tween.kill")
  }

  fun setParallel(parallel: Boolean = true): Tween =
    unsupportedWebGameplayCall("Tween.set_parallel")

  fun tweenProperty(
    target: GodotObject,
    property: String,
    finalValue: Any?,
    duration: Double,
  ): PropertyTweener? = unsupportedWebGameplayCall("Tween.tween_property")

  companion object {
    const val TRANS_BACK = 10L
    const val TRANS_ELASTIC = 6L
    const val EASE_OUT = 1L
  }
}

class PropertyTweener internal constructor(backendHandle: BackendGodotHandle) :
  GodotObject(backendHandle) {
  fun setTrans(transition: Long): PropertyTweener =
    unsupportedWebGameplayCall("PropertyTweener.set_trans")

  fun setEase(ease: Long): PropertyTweener =
    unsupportedWebGameplayCall("PropertyTweener.set_ease")
}

class InputEventMouseButton private constructor(backendHandle: BackendGodotHandle) :
  GodotObject(backendHandle) {
  fun getButtonIndex(): Long =
    unsupportedWebGameplayCall("InputEventMouseButton.get_button_index")

  fun isPressed(): Boolean = unsupportedWebGameplayCall("InputEventMouseButton.is_pressed")

  fun isReleased(): Boolean = unsupportedWebGameplayCall("InputEventMouseButton.is_released")

  companion object {
    const val MOUSE_BUTTON_LEFT = 1L

    fun from(event: GodotObject): InputEventMouseButton? =
      unsupportedWebGameplayCall("InputEventMouseButton.from")
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
    unsupportedWebGameplayCall("SceneTree.quit")
  }

  companion object {
    suspend fun delaySeconds(seconds: Double) {
      unsupportedWebGameplayCall("SceneTree.delay_seconds")
    }
  }
}

internal object WebFrameCoroutineDispatcher : CoroutineDispatcher() {
  private val pending = ArrayDeque<Runnable>()

  val pendingCount: Int
    get() = pending.size

  override fun dispatch(context: kotlin.coroutines.CoroutineContext, block: Runnable) {
    pending.addLast(block)
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
    unsupportedWebGameplayCall("MainThread.post")
  }
}

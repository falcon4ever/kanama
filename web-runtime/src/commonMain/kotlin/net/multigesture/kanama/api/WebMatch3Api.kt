package net.multigesture.kanama.api

import kotlin.math.ln
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import net.multigesture.kanama.backend.GodotHandle as BackendGodotHandle
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

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
    unsupportedWebGameplayCall("Signal.connect_object_method")

  fun connect(
    target: GodotObject,
    argumentCount: Int = 0,
    flags: Long = 0L,
    callback: () -> Unit,
  ): Long = unsupportedWebGameplayCall("Signal.connect_callable")
}

inline fun <reified T : Any> GodotObject.kotlinScriptInstance(): T? =
  unsupportedWebGameplayCall("GodotObject.kotlin_script_instance")

class Area2D(godotObject: GodotHandle) : Node2D(godotObject)

class Viewport(godotObject: GodotHandle) : Node(godotObject.toBackendHandle()) {
  fun getVisibleRect(): Rect2 = unsupportedWebGameplayCall("Viewport.get_visible_rect")
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
  fun instantiate(): GodotObject? = unsupportedWebGameplayCall("PackedScene.instantiate")
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
    unsupportedWebGameplayCall("Input.set_custom_mouse_cursor")
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

class KanamaScope : CoroutineScope {
  private val job = SupervisorJob()
  override val coroutineContext = Dispatchers.Default + job

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

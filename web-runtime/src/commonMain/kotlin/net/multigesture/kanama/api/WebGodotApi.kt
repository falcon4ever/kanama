@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.GDBackendContractProbe
import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.InitialGodotCallDescriptors
import net.multigesture.kanama.backend.ClassDBBackendContractProbe
import net.multigesture.kanama.backend.CanvasItemInputBackendContractProbe
import net.multigesture.kanama.backend.CanvasItemBackendContractProbe
import net.multigesture.kanama.backend.GodotColor
import net.multigesture.kanama.backend.GodotHandle as BackendGodotHandle
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.GodotVector2i
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.backend.Node2DBackendContractProbe
import net.multigesture.kanama.backend.NodeBackendContractProbe
import net.multigesture.kanama.backend.NodeLookupBackendContractProbe
import net.multigesture.kanama.backend.ResourceLoaderBackendContractProbe
import net.multigesture.kanama.backend.SignalBackendContractProbe
import net.multigesture.kanama.backend.Sprite2DBackendContractProbe
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.web.KanamaWebScript
import net.multigesture.kanama.web.WebObjectId
import net.multigesture.kanama.web.webScriptInstance

@RequiresOptIn(
  level = RequiresOptIn.Level.WARNING,
  message = "This API exposes manual Godot resource lifetime management.",
)
@Retention(AnnotationRetention.BINARY)
annotation class ManualGodotLifetimeApi

open class GodotObject internal constructor(internal val backendHandle: BackendGodotHandle) {
  constructor(godotObject: GodotHandle) : this(godotObject.toBackendHandle())

  val handle: GodotHandle
    get() = WebObjectId(backendHandle.backendToken().toInt())

  /** Returns true when both wrappers refer to the same Godot object instance. */
  fun isSameInstance(other: GodotObject): Boolean =
    backendHandle.backendToken() == other.backendHandle.backendToken()

  fun signal(name: String): GodotSignal = GodotSignal(this, name)

  fun emitSignal(signal: String, vararg args: Any?) {
    if (args.isEmpty()) {
      SignalBackendContractProbe(backendHandle).emitNoArgs(signal)
      return
    }
    if (args.size == 1 && args[0] is Int) {
      Node2DBackendContractProbe(backendHandle).emitSignal(signal, args[0] as Int)
      return
    }
    if (args.size == 1 && args[0] is Long) {
      Node2DBackendContractProbe(backendHandle).emitSignal(signal, (args[0] as Long).toInt())
      return
    }
    if (args.size == 1 && args[0] is String) {
      SignalBackendContractProbe(backendHandle)
        .emitString(signal, args[0] as String)
      return
    }
    if (args.size == 1 && args[0] is GodotObject) {
      SignalBackendContractProbe(backendHandle)
        .emitObject(signal, (args[0] as GodotObject).backendHandle)
      return
    }
    if (args.size == 1 && args[0] is Vector2i) {
      val value = args[0] as Vector2i
      SignalBackendContractProbe(backendHandle)
        .emitVector2i(signal, GodotVector2i(value.x, value.y))
      return
    }
    unsupportedWebGameplayFamily("GodotObject.emit_signal_typed")
  }

  companion object {
    const val CONNECT_ONE_SHOT = 4L
  }
}

open class Node internal constructor(backendHandle: BackendGodotHandle) : GodotObject(backendHandle) {
  constructor(godotObject: GodotHandle) : this(godotObject.toBackendHandle())

  fun addChild(node: Node, forceReadableName: Boolean = false, internalMode: Long = 0L) {
    NodeBackendContractProbe(backendHandle)
      .addChild(node.backendHandle, forceReadableName, internalMode)
  }

  fun removeChild(node: Node) {
    NodeBackendContractProbe(backendHandle).removeChild(node.backendHandle)
  }

  fun queueFree() {
    NodeBackendContractProbe(backendHandle).queueFree()
  }

  /** Duplicate this node (flags baked to Godot's default 15); parent the copy via addChild. */
  fun duplicate(): GodotObject? =
    NodeBackendContractProbe(backendHandle).duplicate()?.let(::GodotObject)

  fun getNodeOrNull(path: String): GodotObject? =
    NodeLookupBackendContractProbe(backendHandle).getNodeOrNull(path)?.let(::GodotObject)

  fun getParent(): GodotObject? =
    NodeBackendContractProbe(backendHandle).getParent()?.let(::GodotObject)

  fun <T : GodotObject> getAsOrNull(path: String, ctor: (GodotHandle) -> T): T? =
    getNodeOrNull(path)?.let { ctor(it.handle) }

  fun <T : GodotObject> requireAs(path: String, ctor: (GodotHandle) -> T): T =
    getAsOrNull(path, ctor) ?: error("Required node '$path' was not found")

  fun getTree(): SceneTree =
    SceneTree(
      checkNotNull(NodeBackendContractProbe(backendHandle).getTree()) {
        "Node is not inside a SceneTree"
      }
    )

  fun getViewport(): Viewport? =
    NodeLookupBackendContractProbe(backendHandle).getViewport()?.let { handle ->
      Viewport(WebObjectId(handle.backendToken().toInt()))
    }

  fun createTween(): Tween? = NodeBackendContractProbe(backendHandle).createTween()?.let(::Tween)

  fun isInGroup(group: String): Boolean =
    GodotBackendCalls.invokeStringNameRetBool(
      InitialGodotCallDescriptors.NODE_IS_IN_GROUP,
      backendHandle,
      group,
    )

  /** Dynamic one-String-argument method call (e.g. a native GDScript autoload's play(path)). */
  fun call(method: String, argument: String) {
    GodotBackendCalls.invokeStringNameStringNameArg(
      InitialGodotCallDescriptors.OBJECT_CALL,
      backendHandle,
      method,
      argument,
    )
  }

  /** Object.set_deferred with a Boolean value (physics-safe property writes). */
  fun setDeferred(property: String, value: Boolean) {
    GodotBackendCalls.invokeStringNameBoolArg(
      InitialGodotCallDescriptors.OBJECT_SET_DEFERRED,
      backendHandle,
      property,
      value,
    )
  }
}

open class CanvasItem internal constructor(backendHandle: BackendGodotHandle) : Node(backendHandle) {
  var modulate: Color
    get() =
      CanvasItemBackendContractProbe(backendHandle).modulate.let { value ->
        Color(value.r, value.g, value.b, value.a)
      }
    set(value) {
      CanvasItemBackendContractProbe(backendHandle).modulate =
        GodotColor(value.r, value.g, value.b, value.a)
    }

  fun getViewportRect(): Rect2 =
    Node2DBackendContractProbe(backendHandle).viewportRect.let { rect ->
      Rect2(
        Vector2(rect.position.x.toDouble(), rect.position.y.toDouble()),
        Vector2(rect.size.x.toDouble(), rect.size.y.toDouble()),
      )
    }

  fun queueRedraw() {
    Node2DBackendContractProbe(backendHandle).queueRedraw()
  }

  fun setVisible(value: Boolean) {
    CanvasItemBackendContractProbe(backendHandle).setVisible(value)
  }

  fun show() {
    CanvasItemBackendContractProbe(backendHandle).setVisible(true)
  }

  fun hide() {
    CanvasItemBackendContractProbe(backendHandle).setVisible(false)
  }

  fun isVisible(): Boolean = CanvasItemBackendContractProbe(backendHandle).isVisible()

  fun drawTexture(texture: Texture2D, position: Vector2, modulate: Color) {
    Node2DBackendContractProbe(backendHandle)
      .drawTexture(
        texture.requireOpenHandle(),
        GodotVector2(position.x.toFloat(), position.y.toFloat()),
        GodotColor(modulate.r, modulate.g, modulate.b, modulate.a),
      )
  }
}

open class Node2D(godotObject: GodotHandle) : CanvasItem(godotObject.toBackendHandle()) {
  var position: Vector2
    get() =
      Node2DBackendContractProbe(backendHandle).position.let { value ->
        Vector2(value.x.toDouble(), value.y.toDouble())
      }
    set(value) {
      Node2DBackendContractProbe(backendHandle).position =
        GodotVector2(value.x.toFloat(), value.y.toFloat())
    }

  var scale: Vector2
    get() =
      Node2DBackendContractProbe(backendHandle).scale.let { value ->
        Vector2(value.x.toDouble(), value.y.toDouble())
      }
    set(value) {
      Node2DBackendContractProbe(backendHandle).scale =
        GodotVector2(value.x.toFloat(), value.y.toFloat())
    }

  var rotation: Double
    get() = Node2DBackendContractProbe(backendHandle).getRotation()
    set(value) {
      Node2DBackendContractProbe(backendHandle).setRotation(value)
    }

  fun getLocalMousePosition(): Vector2 =
    CanvasItemInputBackendContractProbe(backendHandle).getLocalMousePosition().let { value ->
      Vector2(value.x.toDouble(), value.y.toDouble())
    }
}

class Sprite2D(godotObject: GodotHandle) : Node2D(godotObject) {
  var texture: Texture2D?
    get() = Sprite2DBackendContractProbe(backendHandle).getTexture()?.let(::Texture2D)
    set(value) {
      setTexture(value)
    }

  fun setTexture(texture: Texture2D?) {
    Sprite2DBackendContractProbe(backendHandle).setTexture(texture?.requireOpenHandle())
  }

  companion object {
    fun create(): Sprite2D {
      val handle =
        checkNotNull(ClassDBBackendContractProbe.instantiate("Sprite2D")) {
          "Godot could not instantiate Sprite2D"
        }
      return Sprite2D(WebObjectId(handle.backendToken().toInt()))
    }
  }
}

abstract class KanamaScript<T : GodotObject>(
  val godotObject: GodotHandle,
  wrapper: (GodotHandle) -> T,
) : KanamaWebScript(godotObject) {
  protected val self: T = wrapper(godotObject)

  inline fun <R> selfAs(ctor: (GodotHandle) -> R): R = ctor(godotObject)
}

open class Resource internal constructor(backendHandle: BackendGodotHandle) :
  GodotObject(backendHandle) {
  constructor(godotObject: GodotHandle) : this(godotObject.toBackendHandle())

  companion object {
    /**
     * Public factory matching desktop, where the `Resource` constructor is internal and
     * `fromHandle` is the public self-factory spelling — so a `KanamaScript<Resource>` script can
     * write `Resource.fromHandle(godotObject)` and compile on either platform (task 64). The web
     * constructor stays public for source compatibility with the already-ported corpus.
     */
    fun fromHandle(handle: GodotHandle): Resource = Resource(handle)

    /** Re-types any Godot object as a Resource (the Web bridge carries no class metadata). */
    fun fromObject(value: GodotObject?): Resource? = value?.let { Resource(it.backendHandle) }
  }
}

@ManualGodotLifetimeApi
class Texture2D internal constructor(private var resourceHandle: BackendGodotHandle?) :
  Resource(checkNotNull(resourceHandle)) {
  constructor(godotObject: GodotHandle) : this(godotObject.toBackendHandle())

  internal fun requireOpenHandle(): BackendGodotHandle =
    checkNotNull(resourceHandle) { "Texture2D is closed" }

  fun close() {
    val handle = resourceHandle ?: return
    releaseWebResource(handle.backendToken().toInt())
    resourceHandle = null
  }
}

object ResourceLoader {
  const val CACHE_MODE_REUSE = 1L

  @ManualGodotLifetimeApi
  fun loadTexture2D(path: String, cacheMode: Long = CACHE_MODE_REUSE): Texture2D? =
    ResourceLoaderBackendContractProbe.load(path, "Texture2D", cacheMode)?.let(::Texture2D)

  fun loadPackedScene(path: String, cacheMode: Long = CACHE_MODE_REUSE): PackedScene? =
    ResourceLoaderBackendContractProbe.load(path, "PackedScene", cacheMode)?.let(::PackedScene)

  /**
   * Loads an audio stream the caller owns (release with [AudioStream.close]) — the acquisition
   * path for `AudioStreamPlayer.setStream` on an already-held stream (task 64). Same admitted
   * loader family (and "AudioStream" type hint) that `setStreamFromPath` already rides.
   */
  @ManualGodotLifetimeApi
  fun loadAudioStream(path: String, cacheMode: Long = CACHE_MODE_REUSE): AudioStream? =
    ResourceLoaderBackendContractProbe.load(path, "AudioStream", cacheMode)?.let(::AudioStream)
}

object GD {
  fun randomize() = GDBackendContractProbe.randomize()

  fun randi(): Long = GDBackendContractProbe.randi()

  fun randf(): Double = GDBackendContractProbe.randf()

  fun randiRange(from: Long, to: Long): Long {
    require(from <= to)
    val range = to - from + 1
    return from + (randi().toULong() % range.toULong()).toLong()
  }

  /** Normally-distributed random (Box-Muller over the engine-seeded randf). */
  fun randfn(mean: Double, deviation: Double): Double {
    val u1 = randf().coerceAtLeast(1e-12)
    val u2 = randf()
    val gaussian =
      kotlin.math.sqrt(-2.0 * kotlin.math.ln(u1)) * kotlin.math.cos(2.0 * kotlin.math.PI * u2)
    return mean + deviation * gaussian
  }

  /** Web adaptation: liveness of the tracked script/browser handle. */
  fun isInstanceValid(instance: GodotObject?): Boolean {
    if (instance == null) return false
    val handle = instance.handle.value
    return webScriptInstance(handle) != null || isWebBrowserHandleLive(handle)
  }

  fun randfRange(from: Double, to: Double): Double {
    require(from <= to)
    return from + (to - from) * randf()
  }

  fun lerpf(from: Double, to: Double, weight: Double): Double = Mathf.lerp(from, to, weight)

  fun signf(value: Double): Double = if (value > 0.0) 1.0 else if (value < 0.0) -1.0 else 0.0

  /** Godot's remap: linear map of [value] from [istart, istop] onto [ostart, ostop]. */
  fun remap(value: Double, istart: Double, istop: Double, ostart: Double, ostop: Double): Double =
    ostart + (ostop - ostart) * ((value - istart) / (istop - istart))

  fun clampf(value: Double, min: Double, max: Double): Double = Mathf.clamp(value, min, max)

  fun lerpAngle(from: Double, to: Double, weight: Double): Double =
    Mathf.lerpAngle(from, to, weight)

  fun degToRad(degrees: Double): Double = degrees * kotlin.math.PI / 180.0

  /** Web adaptation: Godot's print lands on the browser console via Wasm stdout. */
  fun print(message: Any?) {
    println(message)
  }
}

internal fun GodotHandle.toBackendHandle(): BackendGodotHandle =
  BackendGodotHandle.fromBackendToken(value.toLong())

internal expect fun releaseWebResource(resourceHandle: Int)

internal expect fun instantiateWebScript(className: String): Int

internal expect fun releaseWebScriptResource(handle: Int)

internal expect fun releaseWebConstructedObject(handle: Int)

/** Release a tracked OBJECT-kind handle (a duplicated resource the script owns). */
internal expect fun releaseWebTrackedObject(handle: Int)

internal expect fun isWebBrowserHandleLive(handle: Int): Boolean

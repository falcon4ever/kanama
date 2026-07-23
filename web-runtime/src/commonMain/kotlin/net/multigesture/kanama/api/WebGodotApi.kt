@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.GDBackendContractProbe
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

  fun getNodeOrNull(path: String): GodotObject? =
    NodeLookupBackendContractProbe(backendHandle).getNodeOrNull(path)?.let(::GodotObject)

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
  GodotObject(backendHandle)

@ManualGodotLifetimeApi
class Texture2D internal constructor(private var resourceHandle: BackendGodotHandle?) :
  Resource(checkNotNull(resourceHandle)) {
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

  fun randfRange(from: Double, to: Double): Double {
    require(from <= to)
    return from + (to - from) * randf()
  }
}

internal fun GodotHandle.toBackendHandle(): BackendGodotHandle =
  BackendGodotHandle.fromBackendToken(value.toLong())

internal expect fun releaseWebResource(resourceHandle: Int)

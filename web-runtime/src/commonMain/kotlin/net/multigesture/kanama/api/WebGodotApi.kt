@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.GDBackendContractProbe
import net.multigesture.kanama.backend.GodotColor
import net.multigesture.kanama.backend.GodotHandle as BackendGodotHandle
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.backend.Node2DBackendContractProbe
import net.multigesture.kanama.backend.ResourceLoaderBackendContractProbe
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.web.KanamaWebScript

@RequiresOptIn(
  level = RequiresOptIn.Level.WARNING,
  message = "This API exposes manual Godot resource lifetime management.",
)
@Retention(AnnotationRetention.BINARY)
annotation class ManualGodotLifetimeApi

open class GodotObject internal constructor(internal val backendHandle: BackendGodotHandle) {
  fun emitSignal(signal: String, vararg args: Any?) {
    require(args.size == 1 && args[0] is Int) {
      "The initial Kanama Web signal slice supports exactly one Int argument"
    }
    Node2DBackendContractProbe(backendHandle).emitSignal(signal, args[0] as Int)
  }
}

open class Node internal constructor(backendHandle: BackendGodotHandle) : GodotObject(backendHandle)

open class CanvasItem internal constructor(backendHandle: BackendGodotHandle) : Node(backendHandle) {
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

class Node2D(godotObject: GodotHandle) : CanvasItem(godotObject.toBackendHandle())

abstract class KanamaScript<T : GodotObject>(
  godotObject: GodotHandle,
  wrapper: (GodotHandle) -> T,
) : KanamaWebScript(godotObject) {
  protected val self: T = wrapper(godotObject)
}

@ManualGodotLifetimeApi
class Texture2D internal constructor(private var resourceHandle: BackendGodotHandle?) {
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
}

private fun GodotHandle.toBackendHandle(): BackendGodotHandle =
  BackendGodotHandle.fromBackendToken(value.toLong())

internal expect fun releaseWebResource(resourceHandle: Int)

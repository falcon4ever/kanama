package net.multigesture.kanama.web

import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.backend.GodotColor
import net.multigesture.kanama.backend.GodotHandle as BackendGodotHandle
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.backend.Node2DBackendContractProbe
import net.multigesture.kanama.backend.ResourceLoaderBackendContractProbe

/** Real KSP input used to prove that the existing ScriptModel can be produced for wasmJs. */
@ScriptClass(attachTo = "Node2D")
@OptIn(InternalKanamaBackendApi::class)
class WebSpikeScript(objectId: GodotHandle) : KanamaWebScript(objectId) {
  @ScriptProperty var greeting: String = "Hello from Kotlin/Wasm"

  var readyCount: Int = 0
    private set

  var elapsedSeconds: Double = 0.0
    private set

  private var drawTexture: BackendGodotHandle? = null

  @OnReady
  fun ready() {
    readyCount += 1
    drawTexture =
      checkNotNull(
        ResourceLoaderBackendContractProbe.load("res://kanama-draw-probe.svg", "Texture2D")
      )
    selfProbe().queueRedraw()
  }

  @OnProcess
  fun process(delta: Double) {
    elapsedSeconds += delta
  }

  @RegisterFunction("_draw")
  fun draw() {
    val texture = drawTexture ?: return
    selfProbe().drawTexture(texture, GodotVector2(32.0f, 32.0f), GodotColor(1.0f, 1.0f, 1.0f, 1.0f))
  }

  @RegisterFunction fun echo(value: Long): Long = value

  @Signal fun changed(value: Long) = Unit

  private fun selfProbe(): Node2DBackendContractProbe =
    Node2DBackendContractProbe(BackendGodotHandle.fromBackendToken(objectId.value.toLong()))
}

package net.multigesture.kanama.web

import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.Node2D
import net.multigesture.kanama.api.ResourceLoader
import net.multigesture.kanama.api.Texture2D
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/** Real KSP input used to prove that the existing ScriptModel can be produced for wasmJs. */
@ScriptClass(attachTo = "Node2D")
class WebSpikeScript(objectId: GodotHandle) : KanamaWebScript(objectId) {
  @ScriptProperty var greeting: String = "Hello from Kotlin/Wasm"

  var readyCount: Int = 0
    private set

  var elapsedSeconds: Double = 0.0
    private set

  private var drawTexture: Texture2D? = null

  @OnReady
  fun ready() {
    readyCount += 1
    drawTexture = checkNotNull(ResourceLoader.loadTexture2D("res://kanama-draw-probe.svg"))
    self().queueRedraw()
  }

  @OnProcess
  fun process(delta: Double) {
    elapsedSeconds += delta
  }

  @RegisterFunction("_draw")
  fun draw() {
    val texture = drawTexture ?: return
    self().drawTexture(texture, Vector2(32.0, 32.0), Color(1.0f, 1.0f, 1.0f, 1.0f))
  }

  @RegisterFunction fun echo(value: Long): Long = value

  @Signal fun changed(value: Long) = Unit

  private fun self(): Node2D = Node2D(objectId)
}

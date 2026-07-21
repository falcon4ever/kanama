package net.multigesture.kanama.web

import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.annotations.Signal

/** Real KSP input used to prove that the existing ScriptModel can be produced for wasmJs. */
@ScriptClass(attachTo = "Node2D")
class WebSpikeScript(objectId: WebObjectId) : KanamaWebScript(objectId) {
  @ScriptProperty var greeting: String = "Hello from Kotlin/Wasm"

  var readyCount: Int = 0
    private set

  var elapsedSeconds: Double = 0.0
    private set

  @OnReady
  fun ready() {
    readyCount += 1
  }

  @OnProcess
  fun process(delta: Double) {
    elapsedSeconds += delta
  }

  @RegisterFunction fun echo(value: Long): Long = value

  @Signal fun changed(value: Long) = Unit
}

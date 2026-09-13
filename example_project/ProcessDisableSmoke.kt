package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

@ScriptClass(attachTo = "Node")
class ProcessDisableSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {

  @OnReady
  fun ready() {
    self.setProcess(false)
    GD.print("ProcessDisableSmoke ready processing=${self.isProcessing()}")
  }

  @OnProcess
  fun process(delta: Double) {
    GD.print("ProcessDisableSmoke unexpected process delta_nonnegative=${delta >= 0.0}")
  }
}

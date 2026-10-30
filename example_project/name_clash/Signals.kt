package net.multigesture.kanama.example.clash

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * A signal-bus script named `Signals`, as many games name their event autoload; the desktop
 * registrar uses the runtime's `Signals`. See [Arena].
 */
@ScriptClass(attachTo = "Node")
class Signals(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @Export var arena: Node? = null

  @Signal fun scoreChanged(score: Long) = Unit

  fun score(points: Long): Long = points * 2

  @OnReady
  fun ready() {
    scoreChanged(score(1))
  }
}

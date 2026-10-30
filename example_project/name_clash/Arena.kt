package net.multigesture.kanama.example.clash

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * A script class named like a type the generated registrar uses (`java.lang.foreign.Arena`). It
 * only has to compile: the registrar must not import a name a script can take. [Signals] is the
 * second case (a game's signal-bus autoload, and the runtime's `Signals`).
 */
@ScriptClass(attachTo = "Node")
class Arena(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @Export var rounds: Long = 3

  @Export var bus: Node? = null

  @Signal fun roundEnded(round: Long) = Unit

  fun nextRound(round: Long): Long = round + 1

  @OnReady
  fun ready() {
    roundEnded(nextRound(rounds))
  }
}

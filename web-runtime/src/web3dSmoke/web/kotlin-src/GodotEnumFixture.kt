package web3d

import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.Rpc
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Task 128 B compile fixture for the Web emitter: Godot-enum script members (scalar enum and
 * bitfield exports through `@export_custom`, enum `@RegisterFunction` arguments / returns on the
 * INT, packed-return and long-void arms, an enum `@Signal` and `@Rpc` argument). Attached to no
 * node in the web3d scene, so the smoke's probes and budgets are untouched; the Web matrix's web3d
 * build compiles it (and its generated proxy) on every Web PR.
 */
@ScriptClass(attachTo = "Node")
class GodotEnumFixture(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @ScriptProperty var mode: Node.ProcessMode = Node.ProcessMode.ALWAYS

  @ScriptProperty
  var messages: Node.ProcessThreadMessages =
    Node.ProcessThreadMessages.MESSAGES or Node.ProcessThreadMessages.MESSAGES_PHYSICS

  @Signal fun modeChanged(mode: Node.ProcessMode) = Unit

  @RegisterFunction
  fun nextMode(mode: Node.ProcessMode): Node.ProcessMode = Node.ProcessMode(mode.value + 1)

  @RegisterFunction
  fun applyFlags(flags: Node.ProcessThreadMessages) {
    messages = flags
  }

  @RegisterFunction fun currentMode(): Node.ProcessMode = mode

  @Rpc(callLocal = true)
  @RegisterFunction
  fun syncMode(mode: Node.ProcessMode) {
    this.mode = mode
  }
}

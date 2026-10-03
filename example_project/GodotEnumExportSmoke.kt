package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.Rpc
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.generated.GodotEnumExportSmokeSignals

/**
 * Task 128 B: Godot enum value classes as script members. `main.tscn` stores `mode = 3`, `messages
 * = 3` and `modes = [1, 4]`; `main.gd` checks the exported metadata (INT with PROPERTY_HINT_ENUM /
 * _FLAGS and the names-with-values hint string), the stored values and a `@RegisterFunction` round
 * trip, and this script reads the values back typed.
 */
@ScriptClass(attachTo = "Node")
class GodotEnumExportSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @ScriptProperty var mode: Node.ProcessMode = Node.ProcessMode.INHERIT

  // A bitfield: PROPERTY_HINT_FLAGS.
  @ScriptProperty var messages: Node.ProcessThreadMessages = Node.ProcessThreadMessages.MESSAGES

  // The array form: a typed int Array carrying Godot values.
  @ScriptProperty var modes: List<Node.ProcessMode> = emptyList()

  // Not stored in the scene: the folded default (WHEN_PAUSED = 2) is what the editor reports.
  @ScriptProperty var defaulted: Node.ProcessMode = Node.ProcessMode.WHEN_PAUSED

  private var signalled: Node.ProcessMode? = null

  @Signal fun modeChanged(mode: Node.ProcessMode) = Unit

  @RegisterFunction
  fun nextMode(mode: Node.ProcessMode): Node.ProcessMode = Node.ProcessMode(mode.value + 1)

  @RegisterFunction
  fun hasPhysicsMessages(flags: Node.ProcessThreadMessages): Boolean =
    Node.ProcessThreadMessages.MESSAGES_PHYSICS in flags

  // Compiles the RPC helpers with a typed enum argument (GodotEnumValue rides the Variant path).
  @Rpc(callLocal = true)
  @RegisterFunction
  fun syncMode(mode: Node.ProcessMode) {
    this.mode = mode
  }

  @OnReady
  fun ready() {
    // The typed connect* callback receives the enum; the connection is scoped to the one emit
    // (closing early is fine, and Godot releases it with the node anyway since task 131).
    GodotEnumExportSmokeSignals.connectModeChanged(this, GodotObject(godotObject)) {
        signalled = it
      }
      .use { GodotEnumExportSmokeSignals.modeChanged(this, Node.ProcessMode.DISABLED) }
    val typedMode = mode == Node.ProcessMode.ALWAYS
    val typedFlags =
      messages == Node.ProcessThreadMessages.MESSAGES_ALL &&
        Node.ProcessThreadMessages.MESSAGES_PHYSICS in messages
    val typedList = modes == listOf(Node.ProcessMode.PAUSABLE, Node.ProcessMode.DISABLED)
    val typedDefault = defaulted == Node.ProcessMode.WHEN_PAUSED
    val typedSignal = signalled == Node.ProcessMode.DISABLED
    System.err.println(
      "[kanama:kt] GodotEnumExportSmoke typed mode=$typedMode flags=$typedFlags " +
        "list=$typedList default=$typedDefault signal=$typedSignal"
    )
  }
}

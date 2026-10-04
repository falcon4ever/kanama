package net.multigesture.kanama.example

import kotlinx.coroutines.CancellationException
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Signal2
import net.multigesture.kanama.api.Signal3
import net.multigesture.kanama.api.SignalArgType
import net.multigesture.kanama.api.tree
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.SignalCallbackRegistry
import net.multigesture.kanama.types.Vector2

/**
 * Task 134 D4 probe, run by `scripts/runtime_smoke.sh` as its own scene
 * (`typed_signal_smoke.tscn`): the generated typed engine signals (`renamed: Signal0`,
 * `childEnteredTree: Signal1<Node>`) and a typed handle for a runtime-declared signal, against the
 * GDScript probe child (`typed_signal_probe.gd`). Rows: a typed connect (bound to the script by
 * `connect { }`), close, one-shot, deferred, `await` (one value, destructured pair, and cancelled
 * when the emitter is freed), a freed receiver is not called, `emit` of a value type and a Godot
 * enum seen by GDScript exactly as a GDScript emit, a GDScript emit decoded typed (a StringName as
 * String), and a wrongly typed GDScript emit reported instead of calling the lambda. Every lambda
 * is released.
 */
@ScriptClass(attachTo = "Node")
class TypedSignalSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @OnReady
  fun ready() {
    val before = SignalCallbackRegistry.size
    val owner = GodotObject(godotObject)

    // (1) Typed engine signals: Signal0 bound to this script, Signal1<Node> typed child.
    var renamed = 0
    var child = ""
    val renamedConnection = self.renamed.connect { renamed++ }
    val childConnection = self.childEnteredTree.connect { node: Node -> child = node.getName() }
    val probeChild = Node(GodotHandle(ObjectCalls.constructObject("Node")))
    probeChild.setName("TypedChild")
    self.addChild(probeChild)
    self.setName("TypedSignalSmokeA")
    renamedConnection.close()
    childConnection.close()
    self.setName("TypedSignalSmokeB") // closed: not counted
    val typedConnect = renamed == 1 && child == "TypedChild"

    // (2) One-shot.
    var oneShot = 0
    self.renamed.connect(GodotObject.ConnectFlags.ONE_SHOT) { oneShot++ }
    self.setName("TypedSignalSmokeC")
    self.setName("TypedSignalSmokeD")

    // (3) A freed receiver is not called.
    val receiver = Node(GodotHandle(ObjectCalls.constructObject("Node")))
    var receiverFired = 0
    self.renamed.connect(receiver) { receiverFired++ }
    ObjectCalls.destroyObject(receiver.handle.segment)
    self.setName("TypedSignalSmokeE")

    // (4) A runtime-declared signal with a value type, a Godot enum and a String, seen by GDScript.
    owner.addUserSignal("kanama_typed")
    val typed =
      Signal3(
        owner,
        "kanama_typed",
        SignalArgType.valueOf<Vector2>("Vector2", Vector2::class),
        SignalArgType.enumOf("Node.ProcessMode", { Node.ProcessMode(it) }, { it.value }),
        SignalArgType.STRING,
      )
    val probe = self.getNode("Probe")!!
    probe.call("attach", owner)
    var kotlinSeen = ""
    var typedCalls = 0
    val typedConnection =
      typed.connect { v, mode, s ->
        typedCalls++
        kotlinSeen = "$v;${mode.value};$s"
      }
    typed.emit(Vector2(1.5, -2.0), Node.ProcessMode.ALWAYS, "hi")
    val emitSeenByKotlin = kotlinSeen
    val gdscriptSeen = probe.call("get_seen") as String
    probe.call("emit_from_gdscript", owner)
    val fromGdscript = kotlinSeen
    val callsBeforeWrong = typedCalls
    probe.call("emit_wrong_type", owner) // reported as a script error; the lambda does not run
    val wrongTypeSkipped = typedCalls == callsBeforeWrong

    // (5) Deferred.
    var deferred = 0
    val deferredConnection = self.renamed.connect(GodotObject.ConnectFlags.DEFERRED) { deferred++ }
    self.setName("TypedSignalSmokeF")
    val deferredImmediate = deferred

    System.err.println(
      "[kanama:kt] TypedSignalSmoke sync typed_connect=$typedConnect one_shot=$oneShot " +
        "receiver_freed_fired=$receiverFired emit_kotlin=$emitSeenByKotlin emit_gdscript=$gdscriptSeen " +
        "from_gdscript=$fromGdscript wrong_type_skipped=$wrongTypeSkipped deferred_immediate=$deferredImmediate"
    )

    // (6) await: one value, a destructured pair, and an await cancelled by freeing the emitter.
    owner.addUserSignal("kanama_pair")
    val pair = Signal2(owner, "kanama_pair", SignalArgType.LONG, SignalArgType.STRING)
    var awaitedRename = false
    var awaitedPair = ""
    var awaitCancelled = false
    val doomed = Node(GodotHandle(ObjectCalls.constructObject("Node")))
    launch {
      self.renamed.await()
      awaitedRename = true
    }
    launch {
      val (n, s) = pair.await()
      awaitedPair = "$n,$s"
    }
    launch {
      try {
        doomed.renamed.await()
      } catch (e: CancellationException) {
        awaitCancelled = true
      }
    }
    launch {
      nextFrame()
      val deferredLater = deferred
      self.setName("TypedSignalSmokeG")
      pair.emit(7L, "seven")
      ObjectCalls.destroyObject(doomed.handle.segment)
      nextFrame()
      typedConnection.close()
      deferredConnection.close()
      System.err.println(
        "[kanama:kt] TypedSignalSmoke async deferred_later=$deferredLater await_signal0=$awaitedRename " +
          "await_pair=$awaitedPair await_cancelled_on_free=$awaitCancelled " +
          "released=${SignalCallbackRegistry.size == before}"
      )
      self.tree.quit()
    }
  }
}

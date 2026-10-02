package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.ResourceLoader
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.SignalCallbackRegistry

/**
 * Task 131 item 3 (F9) probe, run by `scripts/runtime_smoke.sh` as its own scene
 * (`signal_leak_smoke.tscn`). A lambda connection's closure lives in `SignalCallbackRegistry` until
 * something releases it; before task 131 only `SignalConnection.close()` did, so a discarded handle
 * leaked the closure for the life of the process. Two releases are proved here, each as "the
 * registry is back to its size before the connect":
 * - the receiver is freed (its script instance's free path drops the receiver's entries);
 * - a `CONNECT_ONE_SHOT` connection fires (its entry is dropped on dispatch).
 *
 * The returned `SignalConnection`s are discarded on purpose: that is the leaking shape the demos
 * use.
 */
@ScriptClass(attachTo = "Node")
class SignalLeakSmoke(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  @OnReady
  fun ready() {
    val before = SignalCallbackRegistry.size

    // (a) A lambda bound to a receiver that is then freed. `renamed` fires on a rename in the tree.
    val receiverScript = ResourceLoader.load("res://SignalLeakReceiver.kt", "Script")
    val receiver = Node(GodotHandle(ObjectCalls.constructObject("Node")))
    receiver.setScript(receiverScript)
    var fired = 0
    self.signal("renamed").connect(receiver, 0) { fired++ }
    val connected = SignalCallbackRegistry.size - before
    self.setName("SignalLeakSmokeRenamedA")
    val firedBeforeFree = fired
    ObjectCalls.destroyObject(receiver.handle.segment) // Object.free() on the orphan receiver
    val freeReleased = SignalCallbackRegistry.size == before
    self.setName("SignalLeakSmokeRenamedB") // Godot dropped the connection: must not fire
    receiverScript?.close()

    // (b) A one-shot lambda: fires once, then Godot disconnects it and the entry must go too.
    var oneShotFired = 0
    self.signal("renamed").connect(self, 0, GodotObject.ConnectFlags.ONE_SHOT) { oneShotFired++ }
    val oneShotConnected = SignalCallbackRegistry.size - before
    self.setName("SignalLeakSmokeRenamedC")
    self.setName("SignalLeakSmokeRenamedD")
    val oneShotReleased = SignalCallbackRegistry.size == before

    System.err.println(
      "[kanama:kt] SignalLeakSmoke connected=$connected fired=$firedBeforeFree " +
        "free_released=$freeReleased after_free_fired=${fired - firedBeforeFree} " +
        "one_shot_connected=$oneShotConnected one_shot_fired=$oneShotFired " +
        "one_shot_released=$oneShotReleased"
    )
  }
}

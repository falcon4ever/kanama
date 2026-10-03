package net.multigesture.kanama.example

import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.SignalCallbackRegistry

/**
 * Task 131 item 3 (F9) probe, run by `scripts/runtime_smoke.sh` as its own scene
 * (`signal_leak_smoke.tscn`). A lambda connection's closure lives in `SignalCallbackRegistry` until
 * Godot drops the connection's custom Callable, whose `free_func` releases it; before task 131 only
 * `SignalConnection.close()` did, so a discarded handle leaked the closure for the life of the
 * process. Three releases are proved, each as "the registry is back to its size before the
 * connect":
 * - the receiver is freed;
 * - the emitter is freed while the receiver lives;
 * - a `CONNECT_ONE_SHOT` connection fires.
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
    val receiver = Node(GodotHandle(ObjectCalls.constructObject("Node")))
    var fired = 0
    self.signal("renamed").connect(receiver, 0) { fired++ }
    val connected = SignalCallbackRegistry.size - before
    self.setName("SignalLeakSmokeRenamedA")
    val firedBeforeFree = fired
    ObjectCalls.destroyObject(receiver.handle.segment) // Object.free() on the orphan receiver
    val freeReleased = SignalCallbackRegistry.size == before
    self.setName("SignalLeakSmokeRenamedB") // Godot dropped the connection: must not fire

    // (b) A lambda on an emitter that is freed while the receiver (self) lives.
    val emitter = Node(GodotHandle(ObjectCalls.constructObject("Node")))
    emitter.signal("renamed").connect(self, 0) {}
    val emitterConnected = SignalCallbackRegistry.size - before
    ObjectCalls.destroyObject(emitter.handle.segment)
    val emitterReleased = SignalCallbackRegistry.size == before

    // (c) A one-shot lambda: fires once, then Godot disconnects it and the entry must go too.
    var oneShotFired = 0
    self.signal("renamed").connect(self, 0, GodotObject.ConnectFlags.ONE_SHOT) { oneShotFired++ }
    val oneShotConnected = SignalCallbackRegistry.size - before
    self.setName("SignalLeakSmokeRenamedC")
    self.setName("SignalLeakSmokeRenamedD")
    val oneShotReleased = SignalCallbackRegistry.size == before

    System.err.println(
      "[kanama:kt] SignalLeakSmoke connected=$connected fired=$firedBeforeFree " +
        "free_released=$freeReleased after_free_fired=${fired - firedBeforeFree} " +
        "emitter_connected=$emitterConnected emitter_released=$emitterReleased " +
        "one_shot_connected=$oneShotConnected one_shot_fired=$oneShotFired " +
        "one_shot_released=$oneShotReleased"
    )
  }
}

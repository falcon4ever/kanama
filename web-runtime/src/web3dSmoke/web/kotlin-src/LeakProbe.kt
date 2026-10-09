package web3d

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Harness-only (task 131 item 12): a script that connects Kotlin lambdas to two plain nodes,
 * LeakEmitterA (freed first, with the lambdas on it still connected) and LeakEmitterB (kept), then
 * is itself freed. Main's `leak_probe` reads the registry size at each step: every connection's
 * Kotlin callback entry must go with the thing that held it, as desktop's does.
 */
@ScriptClass(attachTo = "Node")
class LeakProbe(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  var firedOnB = 0L

  /** Six lambda connections: four on A (every delivery shape), two on B. Returns the count made. */
  fun leakConnect(value: Long): Long {
    val a = self.requireAs("../LeakEmitterA", ::Node)
    val b = self.requireAs("../LeakEmitterB", ::Node)
    val none = GodotObject.ConnectFlags(0L)
    val oneShot = GodotObject.ConnectFlags.ONE_SHOT
    a.signal("renamed").connect(self, flags = none) {}
    a.signal("ready").connect(self, flags = oneShot) {}
    a.signal("tree_entered").connectLong(self, none) {}
    a.signal("child_entered_tree").connectObject(self, none) {}
    b.signal("renamed").connect(self, flags = none) { firedOnB += 1 }
    b.signal("ready").connect(self, flags = oneShot) { firedOnB += 100 }
    return 6L
  }

  /** Fires B's one-shot (`ready`) and normal (`renamed`) lambdas once. */
  fun leakFire(value: Long): Long {
    val b = self.requireAs("../LeakEmitterB", ::Node)
    b.emitSignal("ready")
    b.emitSignal("renamed")
    b.emitSignal("ready")
    return firedOnB
  }
}

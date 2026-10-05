package web3d

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node

/**
 * Harness-only (task 134 D1 review S3): a second script that awaits a signal of a node that
 * outlives it. Main's d1_probe calls [d1Arm], then frees this node first; the await's connection
 * on D1Emitter must go with it instead of staying behind on the long-lived emitter.
 */
@ScriptClass(attachTo = "Node")
class D1Router(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  fun d1Arm(value: Long): Long {
    val emitter = self.requireAs("../D1Emitter", ::Node)
    launch { emitter.renamed.await() }
    return 1L
  }
}

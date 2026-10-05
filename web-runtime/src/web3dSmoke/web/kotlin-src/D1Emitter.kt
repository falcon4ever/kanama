package web3d

import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.web.WebExperimentalGenericCall

/**
 * Harness-only (task 134 D1 review S3): the long-lived node D1Router awaits. [d1Awaited] reports
 * whether anything is still connected to its `renamed` signal (the await's watcher).
 */
@ScriptClass(attachTo = "Node")
class D1Emitter(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
  fun d1Awaited(value: Long): Long =
    if (WebExperimentalGenericCall.callImmediate(self, "has_connections", listOf("renamed")).asBoolean()) 1L else 0L
}

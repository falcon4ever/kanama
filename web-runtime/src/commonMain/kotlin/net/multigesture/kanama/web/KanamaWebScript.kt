package net.multigesture.kanama.web

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

/** Opaque Phase 0 registry identity. This is not the post-GO common Godot handle contract. */
data class WebObjectId(val value: Int)

data class WebMemberDescriptor(val id: Int, val name: String)

data class WebScriptDescriptor(
  val id: Int,
  val resourcePath: String,
  val className: String,
  val attachTo: String,
  val properties: List<WebMemberDescriptor>,
  val virtuals: List<WebMemberDescriptor>,
  val methods: List<WebMemberDescriptor>,
  val signals: List<WebMemberDescriptor>,
)

/**
 * Minimal non-JVM script base for the Web feasibility probe.
 *
 * It deliberately does not reuse the JVM [MemorySegment]-based KanamaScript. Phase 0 measures the
 * Web boundary first; Task 57 Phase 0.5 owns the eventual shared handle and wrapper contract.
 */
abstract class KanamaWebScript(val objectId: WebObjectId) {
  protected val scope = CoroutineScope(SupervisorJob())

  internal fun close() {
    scope.cancel("Kanama Web script object was freed")
  }
}

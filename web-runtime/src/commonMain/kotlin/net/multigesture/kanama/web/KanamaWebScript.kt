package net.multigesture.kanama.web

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

/** Generation-tagged Web representation behind the public platform-neutral GodotHandle alias. */
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
 * Web script lifetime base used by the public parity-shaped KanamaScript facade.
 *
 * The platform keeps the same source-facing constructor shape as JVM and iOS while storing a
 * generation-tagged browser handle instead of a native pointer.
 */
abstract class KanamaWebScript(val objectId: WebObjectId) {
  protected val scope = CoroutineScope(SupervisorJob())

  internal fun close() {
    scope.cancel("Kanama Web script object was freed")
  }
}

@PublishedApi internal expect fun webScriptInstance(objectId: Int): Any?

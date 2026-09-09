package net.multigesture.kanama.api

import net.multigesture.kanama.web.KanamaWebScript
import net.multigesture.kanama.web.WebObjectId
import net.multigesture.kanama.web.webScriptInstance

/** An owned scripted resource created from Kotlin; release via [close] when handed off. */
class OwnedScriptResource<out T>
@PublishedApi
internal constructor(
  /** The live Kotlin script instance — your `@ScriptClass(attachTo = "Resource")` type. */
  val instance: T,
  /** The owning resource wrapper. Save it, assign it into a slot, or [close] to release. */
  val resource: ScriptResource,
) : AutoCloseable {
  override fun close() = resource.close()
}

/** Resource wrapper over a live script handle whose creation reference we own. */
class ScriptResource internal constructor(godotObject: GodotHandle) : Resource(godotObject) {
  private var closed = false

  fun close() {
    if (closed) return
    closed = true
    releaseWebScriptResource(handle.value)
  }
}

/**
 * Creates a brand-new, engine-backed script resource of type [T] purely from Kotlin — the Web
 * counterpart of the desktop `newScriptInstance` ("close what you create" applies).
 */
inline fun <reified T : KanamaWebScript> newScriptInstance(): OwnedScriptResource<T> {
  val className =
    checkNotNull(T::class.simpleName) { "Kanama Web script resource type must be named" }
  val handle = newWebScriptResourceHandle(className)
  val instance =
    checkNotNull(webScriptInstance(handle) as? T) {
      "Godot did not hydrate a $className script instance"
    }
  return OwnedScriptResource(instance, newWebScriptResourceWrapper(handle))
}

@PublishedApi
internal fun newWebScriptResourceHandle(className: String): Int {
  val handle = instantiateWebScript(className)
  check(handle != 0) { "Godot could not instantiate script resource $className" }
  return handle
}

@PublishedApi
internal fun newWebScriptResourceWrapper(handle: Int): ScriptResource =
  ScriptResource(WebObjectId(handle))

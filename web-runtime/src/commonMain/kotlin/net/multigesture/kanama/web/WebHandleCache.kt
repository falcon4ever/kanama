package net.multigesture.kanama.web

/**
 * Values a wrapper reads with one engine call and then keeps (`GodotObject.instanceId`, the
 * generator's "cached" members), kept per handle token rather than per wrapper: a fresh wrapper of
 * the same handle (`getChild(0).instanceId` every frame) answers from the first read without
 * crossing, and so does a read after the engine freed the object, as desktop's captured id does.
 * The entry goes when the bridge releases the handle (its token may then name another object).
 */
internal object WebHandleCache {
  private val values = HashMap<Int, HashMap<String, Any?>>()

  fun read(token: Int, member: String, fetch: () -> Any?): Any? {
    val known = values[token]
    if (known != null && member in known) return known[member]
    val value = fetch()
    values.getOrPut(token) { HashMap() }[member] = value
    return value
  }

  fun forget(token: Int) {
    values.remove(token)
  }

  /** The number of handles with a kept value (tests). */
  val size: Int
    get() = values.size
}

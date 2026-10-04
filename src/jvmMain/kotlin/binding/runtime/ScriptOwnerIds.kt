package net.multigesture.kanama.binding.runtime

import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference

/**
 * The owner instance id of every `@ScriptClass` Kotlin instance the runtime created (task 131 item
 * 2, review 2): a custom-script-typed value (`@Export var target: Enemy?`, a `List`/`Map` of them)
 * is handed back to Godot as its owner object, and this id answers "was that owner freed?" without
 * reading the owner's memory. Keyed by object identity and held weakly, so a script the game drops
 * is forgotten; a script class's own `equals`/`hashCode` are never consulted.
 */
internal object ScriptOwnerIds {
  private class Entry(script: Any, val id: Long, queue: ReferenceQueue<Any>) :
    WeakReference<Any>(script, queue) {
    val hash: Int = System.identityHashCode(script)
  }

  private val queue = ReferenceQueue<Any>()
  private val byHash = HashMap<Int, MutableList<Entry>>()

  /** Records [script]'s owner instance [id] (read while the owner is alive). */
  @Synchronized
  fun remember(script: Any, id: Long) {
    expunge()
    val bucket = byHash.getOrPut(System.identityHashCode(script)) { ArrayList(1) }
    bucket.removeAll { it.get() === script }
    bucket += Entry(script, id, queue)
  }

  /** [script]'s owner instance id, or null for a script object the runtime did not create. */
  @Synchronized
  fun idOf(script: Any): Long? {
    expunge()
    return byHash[System.identityHashCode(script)]?.firstOrNull { it.get() === script }?.id
  }

  private fun expunge() {
    while (true) {
      val entry = queue.poll() as Entry? ?: return
      val bucket = byHash[entry.hash] ?: continue
      bucket.remove(entry)
      if (bucket.isEmpty()) byHash.remove(entry.hash)
    }
  }
}

package net.multigesture.kanama.binding.runtime

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

/**
 * The Kotlin closures behind lambda signal connections on desktop/Android (`GodotSignal.connect`
 * with a callback). Godot holds a bound Callable `(receiver, "__kanama_signal_dispatchN", id)`; the
 * receiver's generated script dispatch turns the id back into the closure here.
 *
 * Entries are released on three paths (task 131, F9) -- before that only `SignalConnection.close()`
 * released them, so every connection whose handle was dropped leaked its closure (and everything it
 * captured) for the life of the process:
 * - `SignalConnection.close()` ([unregister]);
 * - the receiver's script instance is freed ([unregisterTarget], called from
 *   `ScriptBridge.siFree`): Godot drops the connection with the receiver, so the closure can never
 *   run again;
 * - a `CONNECT_ONE_SHOT` connection fires ([invoke] removes the entry before running it): Godot
 *   disconnects a one-shot connection when it is emitted.
 */
object SignalCallbackRegistry {
  private class Entry(
    val targetInstanceId: Long,
    val oneShot: Boolean,
    val callback: (List<Any?>) -> Unit,
  )

  private val nextId = AtomicLong(1)
  private val callbacks = ConcurrentHashMap<Long, Entry>()
  private val idsByTarget = ConcurrentHashMap<Long, MutableSet<Long>>()

  /**
   * Registers [callback] for a connection whose receiver has instance id [targetInstanceId] (0 =
   * none tracked). [oneShot] marks a `CONNECT_ONE_SHOT` connection: its entry is dropped when it
   * fires.
   */
  fun register(targetInstanceId: Long, oneShot: Boolean, callback: (List<Any?>) -> Unit): Long {
    val id = nextId.getAndIncrement()
    callbacks[id] = Entry(targetInstanceId, oneShot, callback)
    if (targetInstanceId != 0L) {
      idsByTarget.computeIfAbsent(targetInstanceId) { ConcurrentHashMap.newKeySet() }.add(id)
    }
    return id
  }

  fun unregister(id: Long) {
    val entry = callbacks.remove(id) ?: return
    forgetTarget(entry.targetInstanceId, id)
  }

  /** Drops every entry whose receiver is [targetInstanceId]; returns how many were dropped. */
  fun unregisterTarget(targetInstanceId: Long): Int {
    if (targetInstanceId == 0L) return 0
    val ids = idsByTarget.remove(targetInstanceId) ?: return 0
    var removed = 0
    for (id in ids) {
      if (callbacks.remove(id) != null) removed++
    }
    return removed
  }

  fun invoke(id: Long, args: List<Any?>) {
    val entry = callbacks[id] ?: return
    // Remove a one-shot entry before running it, so a callback that throws is still released.
    if (entry.oneShot) unregister(id)
    entry.callback.invoke(args)
  }

  /** Number of live closures (task 131 leak checks). */
  val size: Int
    get() = callbacks.size

  fun isEmpty(): Boolean = callbacks.isEmpty()

  private fun forgetTarget(targetInstanceId: Long, id: Long) {
    if (targetInstanceId == 0L) return
    idsByTarget.computeIfPresent(targetInstanceId) { _, ids ->
      ids.remove(id)
      if (ids.isEmpty()) null else ids
    }
  }
}

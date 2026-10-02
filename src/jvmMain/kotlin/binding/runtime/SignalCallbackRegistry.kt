package net.multigesture.kanama.binding.runtime

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

/**
 * The Kotlin closures behind lambda signal connections on desktop/Android (`GodotSignal.connect`
 * with a callback), keyed by the id that is the userdata of the connection's custom Callable
 * ([SignalCallables]).
 *
 * An entry is released when Godot drops the Callable -- its `free_func` calls [unregister]: the
 * receiver or the emitter was freed, a `CONNECT_ONE_SHOT` connection fired, the connection was
 * disconnected, or the connect failed -- and by `SignalConnection.close()`. Before task 131 (F9)
 * only `close()` released an entry, so every connection whose handle was dropped leaked its closure
 * and everything it captured for the life of the process.
 */
object SignalCallbackRegistry {
  class Entry(val argumentCount: Int, val callback: (List<Any?>) -> Unit)

  private val nextId = AtomicLong(1)
  private val callbacks = ConcurrentHashMap<Long, Entry>()

  /** Registers [callback], which receives the signal's first [argumentCount] arguments. */
  fun register(argumentCount: Int, callback: (List<Any?>) -> Unit): Long {
    val id = nextId.getAndIncrement()
    callbacks[id] = Entry(argumentCount, callback)
    return id
  }

  fun unregister(id: Long) {
    callbacks.remove(id)
  }

  fun entry(id: Long): Entry? = callbacks[id]

  /**
   * Runs [id]'s closure with [args]. Kept for registrars generated before task 131, whose
   * `__kanama_signal_dispatchN` methods call it; new connections dispatch through
   * [SignalCallables].
   */
  fun invoke(id: Long, args: List<Any?>) {
    callbacks[id]?.callback?.invoke(args)
  }

  /** Number of live closures (task 131 leak checks). */
  val size: Int
    get() = callbacks.size
}

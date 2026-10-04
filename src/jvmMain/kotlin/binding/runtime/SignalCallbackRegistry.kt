package net.multigesture.kanama.binding.runtime

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong
import net.multigesture.kanama.api.ListSignalArgReader
import net.multigesture.kanama.api.SignalArgReader

/**
 * The Kotlin closures behind lambda signal connections on desktop/Android (`GodotSignal.connect`
 * with a callback, and the typed `Signal0` … `Signal5` connections), keyed by the id that is the
 * userdata of the connection's custom Callable ([SignalCallables]).
 *
 * An entry is released when Godot drops the Callable -- its `free_func` calls [release]: the
 * receiver or the emitter was freed, a `CONNECT_ONE_SHOT` connection fired, the connection was
 * disconnected, or the connect failed -- and by `SignalConnection.close()` ([unregister]). Before
 * task 131 (F9) only `close()` released an entry, so every connection whose handle was dropped
 * leaked its closure and everything it captured for the life of the process.
 *
 * Since task 134 D4 an entry dispatches a [SignalArgReader] over Godot's argument array instead of
 * a decoded `List<Any?>`: the typed connections read each argument as its declared type, and
 * nothing is allocated per emission beyond the decoded values.
 */
object SignalCallbackRegistry {
  internal class Entry(
    val argumentCount: Int,
    val onRelease: (() -> Unit)?,
    val dispatch: (SignalArgReader) -> Unit,
  ) {
    /**
     * Where the connection lives (task 133 C2): the emitter's address and instance id, the signal
     * and the receiver's instance id, recorded once the connect call returned; null before.
     */
    @Volatile var connection: Connection? = null
  }

  /** A lambda connection's emitter, signal and receiver ([Entry.connection]). */
  class Connection(
    val emitterAddress: Long,
    val emitterInstanceId: Long,
    val signal: String,
    val receiverInstanceId: Long,
  )

  private val nextId = AtomicLong(1)
  private val callbacks = ConcurrentHashMap<Long, Entry>()

  /** Registers [dispatch], which reads the signal's arguments (at least [argumentCount]). */
  internal fun register(
    argumentCount: Int,
    onRelease: (() -> Unit)?,
    dispatch: (SignalArgReader) -> Unit,
  ): Long {
    val id = nextId.getAndIncrement()
    callbacks[id] = Entry(argumentCount, onRelease, dispatch)
    return id
  }

  /** Registers [callback], which receives the signal's first [argumentCount] arguments decoded. */
  fun register(argumentCount: Int, callback: (List<Any?>) -> Unit): Long =
    register(argumentCount, null, listDispatch(argumentCount, callback))

  /** Records where [id]'s connection lives (see [Entry.connection]). */
  fun noteConnection(id: Long, connection: Connection) {
    callbacks[id]?.connection = connection
  }

  /** The live entries whose connection's receiver is one of [receiverInstanceIds], by id. */
  fun connectionsTo(receiverInstanceIds: Set<Long>): Map<Long, Connection> =
    callbacks.entries
      .mapNotNull { (id, entry) ->
        entry.connection?.takeIf { it.receiverInstanceId in receiverInstanceIds }?.let { id to it }
      }
      .toMap()

  /** Drops [id] without running its release hook (`SignalConnection.close()`). */
  fun unregister(id: Long) {
    callbacks.remove(id)
  }

  /** Godot dropped [id]'s Callable: drops the entry and runs its release hook. */
  fun release(id: Long) {
    callbacks.remove(id)?.onRelease?.let { it() }
  }

  internal fun entry(id: Long): Entry? = callbacks[id]

  /**
   * Runs [id]'s closure with [args]. Kept for registrars generated before task 131, whose
   * `__kanama_signal_dispatchN` methods call it; new connections dispatch through
   * [SignalCallables].
   */
  fun invoke(id: Long, args: List<Any?>) {
    // `?.let { it.dispatch(...) }`, never `?.invoke(`: the Android source remap rewrites
    // `.invoke(` (MethodHandle calls) to `.invokeWithArguments(`.
    callbacks[id]?.let { it.dispatch(ListSignalArgReader(args)) }
  }

  /** Number of live closures (task 131 leak checks). */
  val size: Int
    get() = callbacks.size

  /**
   * The `(List<Any?>) -> Unit` form over a reader: each argument as `GodotObject.call` decodes it.
   */
  internal fun listDispatch(
    argumentCount: Int,
    callback: (List<Any?>) -> Unit,
  ): (SignalArgReader) -> Unit = { args ->
    callback(if (argumentCount == 0) emptyList() else List(argumentCount) { args.value(it) })
  }
}

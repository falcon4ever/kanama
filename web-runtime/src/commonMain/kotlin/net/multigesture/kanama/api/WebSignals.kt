package net.multigesture.kanama.api

import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.web.WebObjectId
import net.multigesture.kanama.web.WebPackedFloats
import net.multigesture.kanama.web.webScriptInstance

/**
 * Signal connections over the generated `GodotObject.connect`/`connectBound`/`disconnectBound`
 * members: Kotlin lambdas are registered here and reached through the proxy's
 * `_kanama_web_signal_dispatch*` methods with the callback id bound into the Callable.
 */
internal object WebSignalCallbackRegistry {
  private data class Entry(
    val ownerHandle: Int,
    val sourceHandle: Int,
    val oneShot: Boolean,
    val callback: (() -> Unit)? = null,
    val objectCallback: ((Int) -> Unit)? = null,
    /**
     * Task 80 slice 2: receives the emitted scalar payload as the proxy packed it. The typed
     * `GodotSignal.connect*` overloads parse it back into the declared type.
     */
    val scalarCallback: ((String) -> Unit)? = null,
    /** Task 134 D1: receives every emitted argument (`_kanama_web_signal_dispatch_args`). */
    val argsCallback: ((WebSignalArgs) -> Unit)? = null,
    /**
     * Task 134 D1: runs when the entry goes away without being closed from Kotlin -- its owner
     * script or (for an await) its emitter was freed -- so an `await` is cancelled instead of
     * never resuming.
     */
    val onRelease: ((String) -> Unit)? = null,
  )

  private var nextId = 1
  private val entries = mutableMapOf<Int, Entry>()

  val size: Int
    get() = entries.size

  fun register(
    ownerHandle: Int,
    sourceHandle: Int,
    oneShot: Boolean,
    callback: () -> Unit,
  ): Int {
    check(nextId > 0) { "Kanama Web signal callback registry exhausted" }
    val id = nextId++
    entries[id] = Entry(ownerHandle, sourceHandle, oneShot, callback = callback)
    return id
  }

  fun registerObject(
    ownerHandle: Int,
    sourceHandle: Int,
    oneShot: Boolean,
    callback: (Int) -> Unit,
  ): Int {
    check(nextId > 0) { "Kanama Web signal callback registry exhausted" }
    val id = nextId++
    entries[id] = Entry(ownerHandle, sourceHandle, oneShot, objectCallback = callback)
    return id
  }

  /** Registers a callback that wants the emitted scalar payload (task 80 slice 2). */
  fun registerScalar(
    ownerHandle: Int,
    sourceHandle: Int,
    oneShot: Boolean,
    callback: (String) -> Unit,
  ): Int {
    check(nextId > 0) { "Kanama Web signal callback registry exhausted" }
    val id = nextId++
    entries[id] = Entry(ownerHandle, sourceHandle, oneShot, scalarCallback = callback)
    return id
  }

  /** Registers a callback that receives every emitted argument (task 134 D1). */
  fun registerArgs(
    ownerHandle: Int,
    sourceHandle: Int,
    oneShot: Boolean,
    onRelease: ((String) -> Unit)? = null,
    callback: (WebSignalArgs) -> Unit,
  ): Int {
    check(nextId > 0) { "Kanama Web signal callback registry exhausted" }
    val id = nextId++
    entries[id] = Entry(ownerHandle, sourceHandle, oneShot, argsCallback = callback, onRelease = onRelease)
    return id
  }

  /** Whether [id] is still registered (a fired one-shot or a released entry is not). */
  fun contains(id: Int): Boolean = id in entries

  fun unregister(id: Int) {
    entries.remove(id)
  }

  /** The proxy's await watcher saw its emitter freed before the signal fired (task 134 D1). */
  fun release(id: Int) {
    entries.remove(id)?.onRelease?.invoke("the emitting object was freed before it fired")
  }

  fun releaseOwner(ownerHandle: Int) =
    releaseWhere("the awaiting script was freed") { it.ownerHandle == ownerHandle }

  fun releaseSource(sourceHandle: Int) =
    releaseWhere("the emitting object was released before it fired") { it.sourceHandle == sourceHandle }

  private fun releaseWhere(reason: String, predicate: (Entry) -> Boolean) {
    val released = entries.filterValues(predicate)
    released.keys.forEach(entries::remove)
    released.values.forEach { it.onRelease?.invoke(reason) }
  }

  /** Delivers every emitted argument, packed by the proxy (task 134 D1). */
  fun dispatchArgs(ownerHandle: Int, id: Int, packed: String) {
    val entry = requireEntry(ownerHandle, id)
    val callback =
      entry.argsCallback ?: error("Kanama Web signal callback id=$id does not take the argument list")
    if (entry.oneShot) entries.remove(id)
    callback(WebSignalArgs(packed))
  }

  fun dispatch(ownerHandle: Int, id: Int) {
    val entry = requireEntry(ownerHandle, id)
    val callback =
      entry.callback ?: error("Kanama Web signal callback id=$id expects an emitted object")
    if (entry.oneShot) entries.remove(id)
    callback()
  }

  /**
   * Delivers one emitted scalar payload (task 80 slice 2).
   *
   * A callback registered without a payload type still runs and simply ignores [packed] — that is
   * what keeps `connect(target, argumentCount = 1) { ... }` and `await(target, 1)` working after
   * the one-argument delivery helper started carrying the payload.
   */
  fun dispatchScalar(ownerHandle: Int, id: Int, packed: String) {
    val entry = requireEntry(ownerHandle, id)
    val scalar = entry.scalarCallback
    val plain = entry.callback
    if (scalar == null && plain == null) {
      error("Kanama Web signal callback id=$id expects an emitted object")
    }
    if (entry.oneShot) entries.remove(id)
    if (scalar != null) scalar(packed) else plain!!()
  }

  fun dispatchObject(ownerHandle: Int, id: Int, argHandle: Int) {
    val entry = requireEntry(ownerHandle, id)
    val callback =
      entry.objectCallback
        ?: error("Kanama Web signal callback id=$id does not accept an emitted object")
    if (entry.oneShot) entries.remove(id)
    callback(argHandle)
  }

  private fun requireEntry(ownerHandle: Int, id: Int): Entry {
    val entry = entries[id] ?: error("Stale Kanama Web signal callback id=$id")
    check(entry.ownerHandle == ownerHandle) {
      "Kanama Web signal callback id=$id belongs to handle=${entry.ownerHandle}, not $ownerHandle"
    }
    return entry
  }
}

class GodotSignal internal constructor(private val owner: GodotObject, internal val name: String) {
  fun connect(
    target: GodotObject,
    method: String,
    flags: GodotObject.ConnectFlags = GodotObject.ConnectFlags(0L),
  ): GodotError =
    owner.connect(name, target, method, flags)

  /**
   * Connects [callback], ignoring whatever the signal emits. [argumentCount] is kept for source
   * compatibility: since task 134 D1 any number of emitted arguments is accepted.
   */
  @Suppress("UNUSED_PARAMETER")
  fun connect(
    target: GodotObject,
    argumentCount: Int = 0,
    flags: GodotObject.ConnectFlags = GodotObject.ConnectFlags(0L),
    callback: () -> Unit,
  ): GodotError = connectArgsConnection(target, flags) { callback() }.error

  /**
   * Connects a one-argument scalar signal and DELIVERS the payload (task 80 slice 2).
   *
   * The proxy packs the emitted value into one string with the same encoding the property channel
   * uses; [parse] turns it back into the declared type. The typed overloads below are the public
   * surface — this is the shared plumbing.
   */
  private fun <T> connectScalar(
    target: GodotObject,
    flags: GodotObject.ConnectFlags,
    parse: (String) -> T,
    callback: (T) -> Unit,
  ): GodotError {
    val callbackId =
      WebSignalCallbackRegistry.registerScalar(
        target.handle.value,
        owner.handle.value,
        oneShot = GodotObject.ConnectFlags.ONE_SHOT in flags,
      ) { packed ->
        callback(parse(packed))
      }
    val result =
      owner.connectBound(name, target, "_kanama_web_signal_dispatch1", callbackId.toLong(), flags)
    if (result != GodotError.OK) WebSignalCallbackRegistry.unregister(callbackId)
    return result
  }

  /** Connects a one-`int` signal, delivering the emitted value. */
  fun connectLong(
    target: GodotObject,
    flags: GodotObject.ConnectFlags = GodotObject.ConnectFlags(0L),
    callback: (Long) -> Unit,
  ): GodotError =
    connectScalar(target, flags, { it.trim().toLong() }, callback)

  /** Connects a one-`float` signal, delivering the emitted value. */
  fun connectDouble(
    target: GodotObject,
    flags: GodotObject.ConnectFlags = GodotObject.ConnectFlags(0L),
    callback: (Double) -> Unit,
  ): GodotError =
    connectScalar(target, flags, { WebPackedFloats.decode(it) }, callback)

  /** Connects a one-`bool` signal, delivering the emitted value. */
  fun connectBoolean(
    target: GodotObject,
    flags: GodotObject.ConnectFlags = GodotObject.ConnectFlags(0L),
    callback: (Boolean) -> Unit,
  ): GodotError =
    connectScalar(target, flags, { it == "1" }, callback)

  /** Connects a one-`String` signal, delivering the emitted value. */
  fun connectString(
    target: GodotObject,
    flags: GodotObject.ConnectFlags = GodotObject.ConnectFlags(0L),
    callback: (String) -> Unit,
  ): GodotError =
    connectScalar(target, flags, { it }, callback)

  /** Connects a one-`Vector2` signal, delivering the emitted value. */
  fun connectVector2(
    target: GodotObject,
    flags: GodotObject.ConnectFlags = GodotObject.ConnectFlags(0L),
    callback: (Vector2) -> Unit,
  ): GodotError =
    connectScalar(
      target,
      flags,
      { packed -> packed.split(',').let { Vector2(WebPackedFloats.decode(it[0]), WebPackedFloats.decode(it[1])) } },
      callback,
    )

  /** Connects a one-`Vector2i` signal, delivering the emitted value. */
  fun connectVector2i(
    target: GodotObject,
    flags: GodotObject.ConnectFlags = GodotObject.ConnectFlags(0L),
    callback: (Vector2i) -> Unit,
  ): GodotError =
    connectScalar(
      target,
      flags,
      { packed ->
        packed.split(',').let { Vector2i(it[0].trim().toInt(), it[1].trim().toInt()) }
      },
      callback,
    )

  /** Connects a one-`Vector3` signal, delivering the emitted value. */
  fun connectVector3(
    target: GodotObject,
    flags: GodotObject.ConnectFlags = GodotObject.ConnectFlags(0L),
    callback: (Vector3) -> Unit,
  ): GodotError =
    connectScalar(
      target,
      flags,
      { packed ->
        packed.split(',').let {
          Vector3(
            WebPackedFloats.decode(it[0]),
            WebPackedFloats.decode(it[1]),
            WebPackedFloats.decode(it[2]),
          )
        }
      },
      callback,
    )

  /**
   * Connects a one-`Color` signal, delivering the emitted value (task 133 C2). The four float32
   * channels cross as decimal text with enough digits to round back to the same float32.
   */
  fun connectColor(
    target: GodotObject,
    flags: GodotObject.ConnectFlags = GodotObject.ConnectFlags(0L),
    callback: (Color) -> Unit,
  ): GodotError =
    connectScalar(
      target,
      flags,
      { packed ->
        packed.split(',').let {
          Color(
            WebPackedFloats.decode(it[0]),
            WebPackedFloats.decode(it[1]),
            WebPackedFloats.decode(it[2]),
            WebPackedFloats.decode(it[3]),
          )
        }
      },
      callback,
    )

  /**
   * Connects a one-argument object signal (e.g. body_entered). The emitted Godot object arrives
   * wrapped as [GodotObject]; resolve a Kanama script via kotlinScriptInstance or re-type it with a
   * wrapper constructor.
   */
  fun connectObject(
    target: GodotObject,
    flags: GodotObject.ConnectFlags = GodotObject.ConnectFlags(0L),
    callback: (GodotObject) -> Unit,
  ): SignalConnection? {
    val callbackId =
      WebSignalCallbackRegistry.registerObject(
        target.handle.value,
        owner.handle.value,
        oneShot = GodotObject.ConnectFlags.ONE_SHOT in flags,
      ) { argHandle ->
        callback(GodotObject(WebObjectId(argHandle)))
      }
    val result =
      owner.connectBound(
        name,
        target,
        "_kanama_web_signal_dispatch_object",
        callbackId.toLong(),
        flags,
      )
    if (result != GodotError.OK) {
      WebSignalCallbackRegistry.unregister(callbackId)
      return null
    }
    return SignalConnection(owner, name, target, callbackId)
  }





  /**
   * Task 134 D1: a connection that receives every emitted argument (`_kanama_web_signal_dispatch_args`,
   * a variadic proxy method with the callback id bound last), whatever the signal's arity.
   */
  internal fun connectArgsConnection(
    target: GodotObject,
    flags: GodotObject.ConnectFlags,
    callback: (WebSignalArgs) -> Unit,
  ): SignalConnection {
    val callbackId =
      WebSignalCallbackRegistry.registerArgs(
        target.handle.value,
        owner.handle.value,
        oneShot = GodotObject.ConnectFlags.ONE_SHOT in flags,
        callback = callback,
      )
    val result = owner.connectBound(name, target, SIGNAL_DISPATCH_ARGS, callbackId.toLong(), flags)
    if (result != GodotError.OK) WebSignalCallbackRegistry.unregister(callbackId)
    return SignalConnection(owner, name, target, callbackId, SIGNAL_DISPATCH_ARGS, result)
  }

  /**
   * Task 134 D1: a one-shot connection for an `await`, delivered through [router]'s proxy but held
   * by the EMITTER: the proxy binds a small watcher object to the emitter's own one-shot
   * connection (no metadata, not persisted), so the watcher dies with the emitter and reports it
   * ([onRelease]) instead of the await never resuming, and a freed [router] drops it. Works for any
   * emitter, scripted or not.
   */
  internal fun connectAwait(
    router: GodotObject,
    onRelease: (String) -> Unit,
    callback: (WebSignalArgs) -> Unit,
  ): SignalConnection {
    val callbackId =
      WebSignalCallbackRegistry.registerArgs(
        router.handle.value,
        owner.handle.value,
        oneShot = true,
        onRelease = onRelease,
        callback = callback,
      )
    val result =
      owner.connectBound(name, router, SIGNAL_AWAIT, callbackId.toLong(), GodotObject.ConnectFlags.ONE_SHOT)
    if (result != GodotError.OK) WebSignalCallbackRegistry.unregister(callbackId)
    return SignalConnection(owner, name, router, callbackId, SIGNAL_AWAIT, result)
  }

  /**
   * Suspends until this signal fires once. [target] is the script whose proxy delivers it; the wait
   * is bound to the emitter (task 134 D1: an emitter freed first cancels it). [argumentCount] is
   * kept for source compatibility: any number of arguments is accepted and ignored.
   */
  @Suppress("UNUSED_PARAMETER")
  suspend fun await(target: GodotObject, argumentCount: Int = 0) {
    suspendCancellableCoroutine { continuation ->
      val connection =
        connectAwait(
          target,
          onRelease = { reason ->
            if (continuation.isActive) {
              continuation.cancel(kotlinx.coroutines.CancellationException("signal $name: $reason"))
            }
          },
        ) {
          if (continuation.isActive) continuation.resume(Unit)
        }
      if (connection.error != GodotError.OK) {
        connection.close()
        continuation.cancel(
          kotlinx.coroutines.CancellationException("connect($name) failed: error=${connection.error}")
        )
      } else {
        continuation.invokeOnCancellation { connection.close() }
      }
    }
  }

  internal companion object {
    // Floats arrive in the protocol-30 packing (`WebPackedFloats`: NaN and the infinities kept).
    fun parseVector2Packed(packed: String): Vector2 =
      packed.split(',').let { Vector2(WebPackedFloats.decode(it[0]), WebPackedFloats.decode(it[1])) }

    fun parseVector2iPacked(packed: String): Vector2i =
      packed.split(',').let { Vector2i(it[0].trim().toInt(), it[1].trim().toInt()) }

    fun parseVector3Packed(packed: String): Vector3 =
      packed.split(',').let {
        Vector3(WebPackedFloats.decode(it[0]), WebPackedFloats.decode(it[1]), WebPackedFloats.decode(it[2]))
      }

    fun parseColorPacked(packed: String): Color =
      packed.split(',').let {
        Color(
          WebPackedFloats.decode(it[0]),
          WebPackedFloats.decode(it[1]),
          WebPackedFloats.decode(it[2]),
          WebPackedFloats.decode(it[3]),
        )
      }
  }

}

/**
 * Task 134 D1: `emit_signal` with an argument list no typed arm carries (several arguments, a
 * float, a bool, a value type), as one immediate generic call so handlers run before it returns.
 * The wasmJs actual routes through `WebExperimentalGenericCall.callImmediate`.
 */
internal expect fun webEmitSignalGeneric(target: GodotObject, signal: String, args: Array<out Any?>)

/** A live bound connection; [close] disconnects and releases the Kotlin callback. */
class SignalConnection
internal constructor(
  private val source: GodotObject,
  private val name: String,
  private val target: GodotObject,
  private val callbackId: Int,
  private val dispatchMethod: String = "_kanama_web_signal_dispatch_object",
  /** Godot's `Error` from the connect call ([GodotError.OK] on success). */
  val error: GodotError = GodotError.OK,
) {
  private var closed = error != GodotError.OK

  fun close() {
    if (closed) return
    closed = true
    // A fired one-shot, or an entry released because its owner or emitter was freed, has nothing
    // left to disconnect (task 134 D1) -- and a freed emitter must not be called.
    if (!WebSignalCallbackRegistry.contains(callbackId)) return
    source.disconnectBound(name, target, dispatchMethod, callbackId.toLong())
    WebSignalCallbackRegistry.unregister(callbackId)
  }
}

/** The proxy's variadic all-arguments delivery helper (task 134 D1). */
internal const val SIGNAL_DISPATCH_ARGS = "_kanama_web_signal_dispatch_args"

/** The proxy's await marker: connect a watcher held by the emitter (task 134 D1). */
internal const val SIGNAL_AWAIT = "_kanama_web_signal_await"

inline fun <reified T : Any> GodotObject.kotlinScriptInstance(): T? =
  webScriptInstance(handle.value) as? T

package net.multigesture.kanama.api

import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.web.WebObjectId
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

  fun unregister(id: Int) {
    entries.remove(id)
  }

  fun releaseOwner(ownerHandle: Int) {
    entries.entries.removeAll { it.value.ownerHandle == ownerHandle }
  }

  fun releaseSource(sourceHandle: Int) {
    entries.entries.removeAll { it.value.sourceHandle == sourceHandle }
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

class GodotSignal internal constructor(private val owner: GodotObject, private val name: String) {
  fun connect(target: GodotObject, method: String, flags: Long = 0L): Long =
    owner.connect(name, target, method, flags)

  fun connect(
    target: GodotObject,
    argumentCount: Int = 0,
    flags: Long = 0L,
    callback: () -> Unit,
  ): Long {
    require(argumentCount in 0..1) {
      "Kanama Web signal lambda callbacks currently support at most one emitted argument"
    }
    val callbackId =
      WebSignalCallbackRegistry.register(
        target.handle.value,
        owner.handle.value,
        oneShot = flags and GodotObject.CONNECT_ONE_SHOT != 0L,
        callback,
      )
    val dispatchMethod =
      if (argumentCount == 0) "_kanama_web_signal_dispatch0" else "_kanama_web_signal_dispatch1"
    val result = owner.connectBound(name, target, dispatchMethod, callbackId.toLong(), flags)
    if (result != 0L) WebSignalCallbackRegistry.unregister(callbackId)
    return result
  }

  /**
   * Connects a one-argument scalar signal and DELIVERS the payload (task 80 slice 2).
   *
   * The proxy packs the emitted value into one string with the same encoding the property channel
   * uses; [parse] turns it back into the declared type. The typed overloads below are the public
   * surface — this is the shared plumbing.
   */
  private fun <T> connectScalar(
    target: GodotObject,
    flags: Long,
    parse: (String) -> T,
    callback: (T) -> Unit,
  ): Long {
    val callbackId =
      WebSignalCallbackRegistry.registerScalar(
        target.handle.value,
        owner.handle.value,
        oneShot = flags and GodotObject.CONNECT_ONE_SHOT != 0L,
      ) { packed ->
        callback(parse(packed))
      }
    val result =
      owner.connectBound(name, target, "_kanama_web_signal_dispatch1", callbackId.toLong(), flags)
    if (result != 0L) WebSignalCallbackRegistry.unregister(callbackId)
    return result
  }

  /** Connects a one-`int` signal, delivering the emitted value. */
  fun connectLong(target: GodotObject, flags: Long = 0L, callback: (Long) -> Unit): Long =
    connectScalar(target, flags, { it.trim().toLong() }, callback)

  /** Connects a one-`float` signal, delivering the emitted value. */
  fun connectDouble(target: GodotObject, flags: Long = 0L, callback: (Double) -> Unit): Long =
    connectScalar(target, flags, { it.trim().toDouble() }, callback)

  /** Connects a one-`bool` signal, delivering the emitted value. */
  fun connectBoolean(target: GodotObject, flags: Long = 0L, callback: (Boolean) -> Unit): Long =
    connectScalar(target, flags, { it == "1" }, callback)

  /** Connects a one-`String` signal, delivering the emitted value. */
  fun connectString(target: GodotObject, flags: Long = 0L, callback: (String) -> Unit): Long =
    connectScalar(target, flags, { it }, callback)

  /** Connects a one-`Vector2` signal, delivering the emitted value. */
  fun connectVector2(target: GodotObject, flags: Long = 0L, callback: (Vector2) -> Unit): Long =
    connectScalar(
      target,
      flags,
      { packed -> packed.split(',').let { Vector2(it[0].toDouble(), it[1].toDouble()) } },
      callback,
    )

  /** Connects a one-`Vector2i` signal, delivering the emitted value. */
  fun connectVector2i(target: GodotObject, flags: Long = 0L, callback: (Vector2i) -> Unit): Long =
    connectScalar(
      target,
      flags,
      { packed ->
        packed.split(',').let { Vector2i(it[0].trim().toInt(), it[1].trim().toInt()) }
      },
      callback,
    )

  /** Connects a one-`Vector3` signal, delivering the emitted value. */
  fun connectVector3(target: GodotObject, flags: Long = 0L, callback: (Vector3) -> Unit): Long =
    connectScalar(
      target,
      flags,
      { packed ->
        packed.split(',').let {
          Vector3(it[0].toDouble(), it[1].toDouble(), it[2].toDouble())
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
    flags: Long = 0L,
    callback: (GodotObject) -> Unit,
  ): SignalConnection? {
    val callbackId =
      WebSignalCallbackRegistry.registerObject(
        target.handle.value,
        owner.handle.value,
        oneShot = flags and GodotObject.CONNECT_ONE_SHOT != 0L,
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
    if (result != 0L) {
      WebSignalCallbackRegistry.unregister(callbackId)
      return null
    }
    return SignalConnection(owner, name, target, callbackId)
  }

  /** Suspends until this signal fires once (a one-shot connection resumes the coroutine). */
  suspend fun await(target: GodotObject, argumentCount: Int = 0) {
    require(argumentCount in 0..1) {
      "Kanama Web signal await currently supports at most one emitted argument"
    }
    suspendCancellableCoroutine { continuation ->
      connect(target, argumentCount, GodotObject.CONNECT_ONE_SHOT) {
        if (continuation.isActive) continuation.resume(Unit)
      }
    }
  }
}

/** A live bound connection; [close] disconnects and releases the Kotlin callback. */
class SignalConnection
internal constructor(
  private val source: GodotObject,
  private val name: String,
  private val target: GodotObject,
  private val callbackId: Int,
) {
  private var closed = false

  fun close() {
    if (closed) return
    closed = true
    source.disconnectBound(name, target, "_kanama_web_signal_dispatch_object", callbackId.toLong())
    WebSignalCallbackRegistry.unregister(callbackId)
  }
}

inline fun <reified T : Any> GodotObject.kotlinScriptInstance(): T? =
  webScriptInstance(handle.value) as? T

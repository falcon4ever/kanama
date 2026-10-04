package net.multigesture.kanama.api

import kotlin.coroutines.resume
import kotlin.reflect.KClass
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import net.multigesture.kanama.binding.runtime.ObjectRuntime

/**
 * The arguments of one signal emission, as the platform received them (task 134 D4). Desktop and
 * Android read Godot's `Variant` array in place (`JvmSignalArgReader`), iOS the tagged cells its C
 * shim marshalled (`IosSignalArgReader`). A reader is only valid during the dispatch it was passed
 * to: a typed signal decodes every argument before it calls the Kotlin callback, so a signal emitted
 * from inside that callback can reuse the same reader.
 *
 * Each method throws [SignalArgumentException] when the emitted value has another Godot type (a
 * GDScript `emit_signal` is not type-checked); the platform reports it as a script error naming the
 * signal and the callback does not run.
 */
internal interface SignalArgReader {
  /** Number of emitted arguments. */
  val count: Int

  /** A Godot `int`. */
  fun long(index: Int): Long

  /** A Godot `float`; an `int` is widened, as GDScript does for a typed `float` parameter. */
  fun double(index: Int): Double

  /** A Godot `bool`. */
  fun bool(index: Int): Boolean

  /** A Godot `String` or `StringName`. */
  fun string(index: Int): String

  /** A Godot `Object`: its handle, or `null` for a null object. */
  fun objectHandle(index: Int): GodotHandle?

  /**
   * Any other value, decoded the way `GodotObject.call` decodes a `Variant` return (value types,
   * collections, `null` for nil); an `Object` comes back as a [GodotObject].
   */
  fun value(index: Int): Any?
}

/**
 * An emitted argument does not have the type the signal declares (or is missing). Thrown while the
 * arguments are decoded, before the callback runs; reported as a script error naming the signal.
 */
internal class SignalArgumentException(message: String, cause: Throwable? = null) :
  IllegalArgumentException(message, cause)

/**
 * Where a typed signal's `emit(…)` writes its arguments (task 134 C review S5): desktop and Android
 * build Godot's Variants in place in a per-thread native frame, iOS collects them for its emit path.
 */
internal interface SignalArgWriter {
  fun long(index: Int, value: Long)

  fun double(index: Int, value: Double)

  fun bool(index: Int, value: Boolean)

  fun string(index: Int, value: String)

  fun obj(index: Int, value: GodotObject?)

  /** Any other value, encoded as `emit_signal`'s untyped path encodes it. */
  fun value(index: Int, value: Any?)
}

/** Wraps an emitted object handle in its Kotlin wrapper class. */
fun interface SignalObjectWrapper<out T> {
  fun wrap(handle: GodotHandle): T?
}

/**
 * How one argument of a typed signal is read from an emission and written for [Signal1.emit] and
 * friends (task 134 D4). The generated engine signals use these; a signal Godot or a GDScript
 * declares at runtime can be given a typed handle with them too:
 * ```kotlin
 * val healthChanged = Signal1(events, "health_changed", SignalArgType.LONG)
 * ```
 * Godot `int` is [LONG], `float` is [DOUBLE], `String`/`StringName` is [STRING], an object is
 * [objectOf], a Godot enum is [enumOf], value types and collections are [valueOf], and an untyped
 * (`Variant`) argument is [VARIANT].
 */
abstract class SignalArgType<T> internal constructor(
  /** The Godot type name, for error messages (`"int"`, `"Node2D"`). */
  val godotType: String
) {
  internal abstract fun read(args: SignalArgReader, index: Int): T

  /** The value handed to Godot's `emit_signal`. */
  internal open fun write(value: T): Any? = value

  /** Writes [value] as emission argument [index]. */
  internal open fun put(writer: SignalArgWriter, index: Int, value: T) {
    when (val raw = write(value)) {
      is GodotObject -> writer.obj(index, raw)
      else -> writer.value(index, raw)
    }
  }

  override fun toString(): String = "SignalArgType($godotType)"

  companion object {
    /** Godot `int`. */
    val LONG: SignalArgType<Long> =
      object : SignalArgType<Long>("int") {
        override fun read(args: SignalArgReader, index: Int): Long = args.long(index)

        override fun put(writer: SignalArgWriter, index: Int, value: Long) = writer.long(index, value)
      }

    /** Godot `float`. */
    val DOUBLE: SignalArgType<Double> =
      object : SignalArgType<Double>("float") {
        override fun read(args: SignalArgReader, index: Int): Double = args.double(index)

        override fun put(writer: SignalArgWriter, index: Int, value: Double) = writer.double(index, value)
      }

    /** Godot `bool`. */
    val BOOLEAN: SignalArgType<Boolean> =
      object : SignalArgType<Boolean>("bool") {
        override fun read(args: SignalArgReader, index: Int): Boolean = args.bool(index)

        override fun put(writer: SignalArgWriter, index: Int, value: Boolean) = writer.bool(index, value)
      }

    /** Godot `String` or `StringName`. */
    val STRING: SignalArgType<String> =
      object : SignalArgType<String>("String") {
        override fun read(args: SignalArgReader, index: Int): String = args.string(index)

        override fun put(writer: SignalArgWriter, index: Int, value: String) = writer.string(index, value)
      }

    /** An untyped (`Variant`) argument, decoded as `GodotObject.call` decodes a return. */
    val VARIANT: SignalArgType<Any?> =
      object : SignalArgType<Any?>("Variant") {
        override fun read(args: SignalArgReader, index: Int): Any? = args.value(index)
      }

    /**
     * An object argument wrapped by [wrap] (`SignalArgType.objectOf("Node2D") { Node2D(it) }`). A
     * null object throws: use [nullableObjectOf] where the signal can emit `null`.
     *
     * A `RefCounted` argument (an `InputEvent`, a `Resource`) is OWNED: the wrapper takes a
     * reference of its own (task 132: `close()` it, or the garbage collector releases it), because
     * the value can outlive the emission -- a lambda keeps it (GDScript `kept = event`) and
     * `await()` resumes a frame later, when Godot has dropped its own reference. Any other object
     * (a `Node`) is a view, as in GDScript: a node freed in the frame its signal fired is gone by
     * the time `await()` resumes, and a call through it reports the freed object.
     */
    fun <T : Any> objectOf(godotType: String, wrap: SignalObjectWrapper<T>): SignalArgType<T> =
      object : SignalArgType<T>(godotType) {
        override fun read(args: SignalArgReader, index: Int): T {
          val handle =
            args.objectHandle(index)
              ?: throw SignalArgumentException("argument ${index + 1}: expected $godotType, got null")
          return owning(wrap.wrap(handle))
            ?: throw SignalArgumentException("argument ${index + 1}: expected $godotType, got null")
        }
      }

    /** An object argument that may be `null`; ownership as for [objectOf]. */
    fun <T : Any> nullableObjectOf(godotType: String, wrap: SignalObjectWrapper<T>): SignalArgType<T?> =
      object : SignalArgType<T?>(godotType) {
        override fun read(args: SignalArgReader, index: Int): T? =
          args.objectHandle(index)?.let { owning(wrap.wrap(it)) }
      }

    /** A `RefCounted` wrapper takes its own reference (see [objectOf]); others pass through. */
    private fun <T> owning(value: T): T {
      if (value is RefCounted) RefCounted.retained(value)
      return value
    }

    /**
     * A Godot enum or bitfield carried as `int`, wrapped in its generated value class
     * (`SignalArgType.enumOf("Node.ProcessMode", { Node.ProcessMode(it) }, { it.value })`).
     */
    fun <E> enumOf(godotType: String, wrap: (Long) -> E, raw: (E) -> Long): SignalArgType<E> =
      object : SignalArgType<E>(godotType) {
        override fun read(args: SignalArgReader, index: Int): E = wrap(args.long(index))

        override fun write(value: E): Any? = raw(value)

        override fun put(writer: SignalArgWriter, index: Int, value: E) = writer.long(index, raw(value))
      }

    /**
     * A value type or collection ([type] is its Kotlin class: `Vector2::class`, `List::class`,
     * `Map::class`, `ByteArray::class`). A value of another type throws.
     */
    fun <T : Any> valueOf(godotType: String, type: KClass<*>): SignalArgType<T> =
      object : SignalArgType<T>(godotType) {
        override fun read(args: SignalArgReader, index: Int): T {
          val value = args.value(index)
          if (value == null || !type.isInstance(value)) {
            throw SignalArgumentException(
              "argument ${index + 1}: expected $godotType, got ${value?.let { it::class.simpleName } ?: "null"}"
            )
          }
          @Suppress("UNCHECKED_CAST")
          return value as T
        }
      }
  }
}

/**
 * The common part of [Signal0] … [Signal5]: a named signal on the object that emits it.
 *
 * GDScript's `area.body_entered` is `area.bodyEntered` in Kotlin, one generated property per engine
 * signal, typed from Godot's API (`Signal1<Node2D>`). Connect a lambda with
 * `connect(target) { body -> … }` (or `connect { … }` inside a `KanamaScript`, which binds it to
 * the script's node, as a GDScript lambda is bound to its script), suspend on it with `await()`, and
 * emit it with `emit(…)`. A lambda connection lives as long as its target and the emitting object:
 * Godot drops it (and Kanama releases the lambda) when either is freed, or after one call with
 * [GodotObject.ConnectFlags.ONE_SHOT]; [SignalConnection.close] disconnects it earlier.
 */
abstract class TypedSignal internal constructor(
  /** The object that emits this signal. */
  val emitter: GodotObject,
  /** Godot's name of the signal (`"body_entered"`). */
  val name: String,
  private val argumentCount: Int,
) {
  /** Connects this signal to the Godot-facing [method] on [target]; returns Godot's `Error`. */
  fun connect(target: GodotObject, method: String): GodotError =
    emitter.connect(name, target, method, GodotObject.ConnectFlags(0L))

  /** Connects this signal to the Godot-facing [method] on [target] with [flags]. */
  fun connect(target: GodotObject, method: String, flags: GodotObject.ConnectFlags): GodotError =
    emitter.connect(name, target, method, flags)

  /** Disconnects the [target]/[method] connection made by `connect(target, method)`. */
  fun disconnect(target: GodotObject, method: String) {
    emitter.disconnect(name, target, method)
  }

  /** Whether [target]'s [method] is connected to this signal. */
  fun isConnected(target: GodotObject, method: String): Boolean =
    emitter.isConnected(name, target, method)

  /** Whether anything is connected to this signal (Godot `Object.has_connections`). */
  fun hasConnections(): Boolean = emitter.hasConnections(name)

  /** The untyped handle for this signal. */
  fun untyped(): GodotSignal = emitter.signal(name)

  internal fun connectArgs(
    target: GodotObject,
    flags: GodotObject.ConnectFlags,
    dispatch: (SignalArgReader) -> Unit,
  ): SignalConnection =
    emitter.signal(name).connectArgs(target, argumentCount, flags, null) { args ->
      try {
        dispatch(args)
      } catch (e: SignalArgumentException) {
        throw SignalArgumentException("signal '$name': ${e.message}", e)
      }
    }

  /**
   * One-shot connection that resumes the caller with the decoded arguments. The connection is bound
   * to the emitter, so Godot drops it when the emitter is freed; the waiting coroutine is then
   * cancelled (a GDScript `await` on a freed object never resumes either). Cancelling the
   * coroutine — for example when the script that awaits is freed — disconnects it.
   */
  internal suspend fun <R> awaitArgs(decode: (SignalArgReader) -> R): R =
    suspendCancellableCoroutine { continuation ->
      val connection =
        emitter
          .signal(name)
          .connectArgs(
            emitter,
            argumentCount,
            GodotObject.ConnectFlags.ONE_SHOT,
            onRelease = {
              if (continuation.isActive) {
                continuation.cancel(
                  CancellationException("signal $name: the emitting object was freed before it fired")
                )
              }
            },
          ) { args ->
            // A decode failure resumes the awaiting coroutine with it (reported by the script's
            // scope) instead of leaving it suspended.
            val value = runCatching { decode(args) }
            if (continuation.isActive) {
              value.fold(
                onSuccess = { continuation.resume(it) },
                onFailure = {
                  continuation.resumeWith(
                    Result.failure(SignalArgumentException("signal '$name': ${it.message}", it))
                  )
                },
              )
            }
          }
      if (connection.error != GodotError.OK) {
        connection.close()
        continuation.cancel(CancellationException("connect($name) failed: error=${connection.error}"))
      } else {
        continuation.invokeOnCancellation { connection.close() }
      }
    }

  /**
   * Emits with [argumentCount] arguments written by [fill] straight into the platform's emission
   * frame: no argument list, no boxing beyond the generic parameters (task 134 C review S5).
   */
  internal inline fun emitWith(fill: (SignalArgWriter) -> Unit) {
    val writer = ObjectRuntime.beginEmit(emitter.segment, name, argumentCount)
    var send = false
    try {
      fill(writer)
      send = true
    } finally {
      ObjectRuntime.finishEmit(writer, send)
    }
  }

  override fun toString(): String = "Signal($name on $emitter)"
}

private val NO_FLAGS = GodotObject.ConnectFlags(0L)

/** A signal without arguments (`Timer.timeout`, `BaseButton.pressed`). See [TypedSignal]. */
class Signal0(emitter: GodotObject, name: String) : TypedSignal(emitter, name, 0) {
  /** Connects [callback], bound to [target]. */
  fun connect(target: GodotObject, callback: () -> Unit): SignalConnection = connect(target, NO_FLAGS, callback)

  /** Connects [callback], bound to [target], with [flags] (`ConnectFlags.ONE_SHOT`, `DEFERRED`). */
  fun connect(target: GodotObject, flags: GodotObject.ConnectFlags, callback: () -> Unit): SignalConnection =
    connectArgs(target, flags) { callback() }

  /** Suspends until the signal fires (GDScript `await timer.timeout`). */
  suspend fun await() {
    awaitArgs {}
  }

  /** Emits the signal. */
  fun emit() {
    emitWith {}
  }
}

/** A signal with one argument (`Area2D.bodyEntered: Signal1<Node2D>`). See [TypedSignal]. */
class Signal1<A>(emitter: GodotObject, name: String, private val a: SignalArgType<A>) :
  TypedSignal(emitter, name, 1) {
  /** Connects [callback], bound to [target]. */
  fun connect(target: GodotObject, callback: (A) -> Unit): SignalConnection = connect(target, NO_FLAGS, callback)

  /** Connects [callback], bound to [target], with [flags]. */
  fun connect(target: GodotObject, flags: GodotObject.ConnectFlags, callback: (A) -> Unit): SignalConnection =
    connectArgs(target, flags) { args -> callback(a.read(args, 0)) }

  /** Suspends until the signal fires and returns its argument (GDScript `var body = await area.body_entered`). */
  suspend fun await(): A = awaitArgs { args -> a.read(args, 0) }

  /** Emits the signal. */
  fun emit(a: A) {
    emitWith { w -> this.a.put(w, 0, a) }
  }
}

/** A signal with two arguments. See [TypedSignal]; [await] returns a [SignalArgs2] to destructure. */
class Signal2<A, B>(
  emitter: GodotObject,
  name: String,
  private val a: SignalArgType<A>,
  private val b: SignalArgType<B>,
) : TypedSignal(emitter, name, 2) {
  /** Connects [callback], bound to [target]. */
  fun connect(target: GodotObject, callback: (A, B) -> Unit): SignalConnection = connect(target, NO_FLAGS, callback)

  /** Connects [callback], bound to [target], with [flags]. */
  fun connect(target: GodotObject, flags: GodotObject.ConnectFlags, callback: (A, B) -> Unit): SignalConnection =
    connectArgs(target, flags) { args -> callback(a.read(args, 0), b.read(args, 1)) }

  /** Suspends until the signal fires: `val (first, second) = signal.await()`. */
  suspend fun await(): SignalArgs2<A, B> = awaitArgs { args -> SignalArgs2(a.read(args, 0), b.read(args, 1)) }

  /** Emits the signal. */
  fun emit(a: A, b: B) {
    emitWith { w ->
      this.a.put(w, 0, a)
      this.b.put(w, 1, b)
    }
  }
}

/** A signal with three arguments. See [TypedSignal]. */
class Signal3<A, B, C>(
  emitter: GodotObject,
  name: String,
  private val a: SignalArgType<A>,
  private val b: SignalArgType<B>,
  private val c: SignalArgType<C>,
) : TypedSignal(emitter, name, 3) {
  /** Connects [callback], bound to [target]. */
  fun connect(target: GodotObject, callback: (A, B, C) -> Unit): SignalConnection =
    connect(target, NO_FLAGS, callback)

  /** Connects [callback], bound to [target], with [flags]. */
  fun connect(target: GodotObject, flags: GodotObject.ConnectFlags, callback: (A, B, C) -> Unit): SignalConnection =
    connectArgs(target, flags) { args -> callback(a.read(args, 0), b.read(args, 1), c.read(args, 2)) }

  /** Suspends until the signal fires: `val (x, y, z) = signal.await()`. */
  suspend fun await(): SignalArgs3<A, B, C> =
    awaitArgs { args -> SignalArgs3(a.read(args, 0), b.read(args, 1), c.read(args, 2)) }

  /** Emits the signal. */
  fun emit(a: A, b: B, c: C) {
    emitWith { w ->
      this.a.put(w, 0, a)
      this.b.put(w, 1, b)
      this.c.put(w, 2, c)
    }
  }
}

/** A signal with four arguments. See [TypedSignal]. */
class Signal4<A, B, C, D>(
  emitter: GodotObject,
  name: String,
  private val a: SignalArgType<A>,
  private val b: SignalArgType<B>,
  private val c: SignalArgType<C>,
  private val d: SignalArgType<D>,
) : TypedSignal(emitter, name, 4) {
  /** Connects [callback], bound to [target]. */
  fun connect(target: GodotObject, callback: (A, B, C, D) -> Unit): SignalConnection =
    connect(target, NO_FLAGS, callback)

  /** Connects [callback], bound to [target], with [flags]. */
  fun connect(
    target: GodotObject,
    flags: GodotObject.ConnectFlags,
    callback: (A, B, C, D) -> Unit,
  ): SignalConnection =
    connectArgs(target, flags) { args ->
      callback(a.read(args, 0), b.read(args, 1), c.read(args, 2), d.read(args, 3))
    }

  /** Suspends until the signal fires: `val (a, b, c, d) = signal.await()`. */
  suspend fun await(): SignalArgs4<A, B, C, D> =
    awaitArgs { args -> SignalArgs4(a.read(args, 0), b.read(args, 1), c.read(args, 2), d.read(args, 3)) }

  /** Emits the signal. */
  fun emit(a: A, b: B, c: C, d: D) {
    emitWith { w ->
      this.a.put(w, 0, a)
      this.b.put(w, 1, b)
      this.c.put(w, 2, c)
      this.d.put(w, 3, d)
    }
  }
}

/** A signal with five arguments. See [TypedSignal]. */
class Signal5<A, B, C, D, E>(
  emitter: GodotObject,
  name: String,
  private val a: SignalArgType<A>,
  private val b: SignalArgType<B>,
  private val c: SignalArgType<C>,
  private val d: SignalArgType<D>,
  private val e: SignalArgType<E>,
) : TypedSignal(emitter, name, 5) {
  /** Connects [callback], bound to [target]. */
  fun connect(target: GodotObject, callback: (A, B, C, D, E) -> Unit): SignalConnection =
    connect(target, NO_FLAGS, callback)

  /** Connects [callback], bound to [target], with [flags]. */
  fun connect(
    target: GodotObject,
    flags: GodotObject.ConnectFlags,
    callback: (A, B, C, D, E) -> Unit,
  ): SignalConnection =
    connectArgs(target, flags) { args ->
      callback(a.read(args, 0), b.read(args, 1), c.read(args, 2), d.read(args, 3), e.read(args, 4))
    }

  /** Suspends until the signal fires: `val (a, b, c, d, e) = signal.await()`. */
  suspend fun await(): SignalArgs5<A, B, C, D, E> =
    awaitArgs { args ->
      SignalArgs5(a.read(args, 0), b.read(args, 1), c.read(args, 2), d.read(args, 3), e.read(args, 4))
    }

  /** Emits the signal. */
  fun emit(a: A, b: B, c: C, d: D, e: E) {
    emitWith { w ->
      this.a.put(w, 0, a)
      this.b.put(w, 1, b)
      this.c.put(w, 2, c)
      this.d.put(w, 3, d)
      this.e.put(w, 4, e)
    }
  }
}

/** The arguments of one [Signal2] emission. */
data class SignalArgs2<out A, out B>(val first: A, val second: B)

/** The arguments of one [Signal3] emission. */
data class SignalArgs3<out A, out B, out C>(val first: A, val second: B, val third: C)

/** The arguments of one [Signal4] emission. */
data class SignalArgs4<out A, out B, out C, out D>(val first: A, val second: B, val third: C, val fourth: D)

/** The arguments of one [Signal5] emission. */
data class SignalArgs5<out A, out B, out C, out D, out E>(
  val first: A,
  val second: B,
  val third: C,
  val fourth: D,
  val fifth: E,
)

package net.multigesture.kanama.api

import kotlin.coroutines.resume
import kotlin.reflect.KClass
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.web.WebObjectId
import net.multigesture.kanama.web.WebPackedValues
import net.multigesture.kanama.web.webScriptInstance

/** Wraps an emitted object handle in its Kotlin wrapper class. */
fun interface SignalObjectWrapper<out T> {
  fun wrap(handle: GodotHandle): T?
}

/**
 * The emitted arguments of one signal, as the proxy packed them (task 134 D1): each a
 * `<Variant.Type>:<payload>` ([WebPackedValues.encodeVariant]'s format), an object as its bridge
 * handle id -- valid while the callback runs, like every Web object argument.
 */
internal class WebSignalArgs(packed: String) {
  private val parts: List<String> = if (packed.isEmpty()) emptyList() else packed.split('\u001F')

  val size: Int
    get() = parts.size

  private fun part(index: Int): String {
    require(index < parts.size) { "argument $index: the signal emitted ${parts.size} argument(s)" }
    return parts[index]
  }

  private fun type(index: Int): Int = WebPackedValues.variantType(part(index))

  private fun mismatch(index: Int, expected: String): Nothing =
    throw IllegalArgumentException("argument $index: expected $expected, got Variant type ${type(index)}")

  fun long(index: Int): Long =
    if (type(index) == WebPackedValues.TYPE_INT) WebPackedValues.variantPayload(part(index)).toLong()
    else mismatch(index, "int")

  fun double(index: Int): Double =
    when (type(index)) {
      WebPackedValues.TYPE_FLOAT,
      WebPackedValues.TYPE_INT -> (WebPackedValues.decodeVariant(part(index)) as Number).toDouble()
      else -> mismatch(index, "float")
    }

  fun bool(index: Int): Boolean =
    if (type(index) == WebPackedValues.TYPE_BOOL) WebPackedValues.variantPayload(part(index)) == "1"
    else mismatch(index, "bool")

  fun string(index: Int): String =
    when (type(index)) {
      WebPackedValues.TYPE_STRING,
      WebPackedValues.TYPE_STRING_NAME -> WebPackedValues.decodeVariant(part(index)) as String
      WebPackedValues.TYPE_NODE_PATH -> (WebPackedValues.decodeVariant(part(index)) as net.multigesture.kanama.types.NodePath).path
      else -> mismatch(index, "String")
    }

  /** An object argument's handle, or null for a null object. */
  fun objectHandle(index: Int): GodotHandle? =
    when (type(index)) {
      WebPackedValues.TYPE_NIL -> null
      WebPackedValues.TYPE_OBJECT ->
        WebPackedValues.variantPayload(part(index)).toInt().takeIf { it != 0 }?.let { WebObjectId(it) }
      else -> mismatch(index, "Object")
    }

  /** Any argument as Kotlin (`GodotObject` for an object, see [WebPackedValues.decodeVariant]). */
  fun value(index: Int): Any? =
    if (type(index) == WebPackedValues.TYPE_OBJECT) objectHandle(index)?.let { GodotObject(it) }
    else WebPackedValues.decodeVariant(part(index))

  /** A value-type (or RID / NodePath) argument of [type]. */
  fun value(index: Int, type: KClass<*>): Any {
    val expected =
      when (type) {
        RID::class -> WebPackedValues.TYPE_RID
        NodePath::class -> WebPackedValues.TYPE_NODE_PATH
        else -> WebPackedValues.variantTypeOf(type)
      }
    if (type(index) != expected) mismatch(index, type.simpleName ?: "a value type")
    return WebPackedValues.decodeVariant(part(index))!!
  }
}

/**
 * The Web counterpart of the native `SignalArgType` (task 134 C/D1): how a typed signal's argument
 * is read from an emission and written for `emit`. Every argument the proxy can pack is delivered:
 * `int`, `float`, `bool`, `String`/`StringName`, an object, a Godot enum, any value type (`Vector2`
 * … `Projection`, `Color`) and a `Variant` of those. Any other type throws
 * [UnsupportedOperationException] when it is read, never a silent default.
 */
abstract class SignalArgType<T> internal constructor(
  /** The Godot type name (`"int"`, `"Node3D"`). */
  val godotType: String
) {
  /** Reads argument [index] of an emission. */
  internal abstract fun read(args: WebSignalArgs, index: Int): T

  /** The value handed to `emit_signal`. */
  internal open fun write(value: T): Any? = value

  override fun toString(): String = "SignalArgType($godotType)"

  companion object {
    /** Godot `int`. */
    val LONG: SignalArgType<Long> =
      object : SignalArgType<Long>("int") {
        override fun read(args: WebSignalArgs, index: Int): Long = args.long(index)
      }

    /** Godot `float`. */
    val DOUBLE: SignalArgType<Double> =
      object : SignalArgType<Double>("float") {
        override fun read(args: WebSignalArgs, index: Int): Double = args.double(index)
      }

    /** Godot `bool`. */
    val BOOLEAN: SignalArgType<Boolean> =
      object : SignalArgType<Boolean>("bool") {
        override fun read(args: WebSignalArgs, index: Int): Boolean = args.bool(index)
      }

    /** Godot `String` or `StringName`. */
    val STRING: SignalArgType<String> =
      object : SignalArgType<String>("String") {
        override fun read(args: WebSignalArgs, index: Int): String = args.string(index)
      }

    /** A `Variant` argument: null, Boolean, Long, Double, String, NodePath, a value type or a GodotObject. */
    val VARIANT: SignalArgType<Any?> =
      object : SignalArgType<Any?>("Variant") {
        override fun read(args: WebSignalArgs, index: Int): Any? = args.value(index)
      }

    /** An object argument wrapped by [wrap]; a null object throws (use [nullableObjectOf]). */
    fun <T : Any> objectOf(godotType: String, wrap: SignalObjectWrapper<T>): SignalArgType<T> =
      object : SignalArgType<T>(godotType) {
        override fun read(args: WebSignalArgs, index: Int): T =
          args.objectHandle(index)?.let { wrap.wrap(it) }
            ?: throw IllegalArgumentException("argument $index: expected $godotType, got null")
      }

    /** An object argument that may be `null`. */
    fun <T : Any> nullableObjectOf(godotType: String, wrap: SignalObjectWrapper<T>): SignalArgType<T?> =
      object : SignalArgType<T?>(godotType) {
        override fun read(args: WebSignalArgs, index: Int): T? = args.objectHandle(index)?.let { wrap.wrap(it) }
      }

    /** A Godot enum or bitfield carried as `int`. */
    fun <E> enumOf(godotType: String, wrap: (Long) -> E, raw: (E) -> Long): SignalArgType<E> =
      object : SignalArgType<E>(godotType) {
        override fun read(args: WebSignalArgs, index: Int): E = wrap(args.long(index))

        override fun write(value: E): Any? = raw(value)
      }

    /** A value type (`Vector2` … `Projection`, `Color`), a RID or a NodePath; any other class throws when read. */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> valueOf(godotType: String, type: KClass<*>): SignalArgType<T> =
      object : SignalArgType<T>(godotType) {
        override fun read(args: WebSignalArgs, index: Int): T {
          if (WebPackedValues.variantTypeOf(type) == 0 && type != RID::class && type != NodePath::class) {
            throw UnsupportedOperationException("argument $index: a $godotType argument is not delivered on Web yet")
          }
          return args.value(index, type) as T
        }
      }
  }
}

/**
 * A named signal on the object that emits it, typed (task 134 C; any arity on Web since D1); see
 * the native `TypedSignal`. A lambda connection is bound to its target script; an [await] is bound
 * to the emitter's lifetime, so an emitter freed before it fires cancels the waiting coroutine (as
 * on desktop/iOS) instead of leaving it suspended.
 */
abstract class TypedSignal internal constructor(
  /** The object that emits this signal. */
  val emitter: GodotObject,
  /** Godot's name of the signal. */
  val name: String,
) {
  /** Connects this signal to the Godot-facing [method] on [target]. */
  fun connect(target: GodotObject, method: String): GodotError =
    emitter.connect(name, target, method, GodotObject.ConnectFlags(0L))

  /** Connects this signal to [method] on [target] with [flags]. */
  fun connect(target: GodotObject, method: String, flags: GodotObject.ConnectFlags): GodotError =
    emitter.connect(name, target, method, flags)

  /** The untyped handle for this signal. */
  fun untyped(): GodotSignal = emitter.signal(name)

  internal fun connectArgs(
    target: GodotObject,
    flags: GodotObject.ConnectFlags,
    dispatch: (WebSignalArgs) -> Unit,
  ): SignalConnection =
    emitter.signal(name).connectArgsConnection(target, flags) { args ->
      try {
        dispatch(args)
      } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("signal '$name': ${e.message}", e)
      }
    }

  internal suspend fun <R> awaitArgs(decode: (WebSignalArgs) -> R): R =
    suspendCancellableCoroutine { continuation ->
      val connection =
        emitter
          .signal(name)
          .connectAwait(
            awaitRouter(),
            onRelease = { reason ->
              // The emitter freed first, or the awaiting script (the router) freed first.
              if (continuation.isActive) continuation.cancel(CancellationException("signal $name: $reason"))
            },
          ) { args ->
            val value = runCatching { decode(args) }
            if (continuation.isActive) {
              value.fold(
                onSuccess = { continuation.resume(it) },
                onFailure = {
                  continuation.resumeWith(
                    Result.failure(IllegalArgumentException("signal '$name': ${it.message}", it))
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
   * The script whose proxy delivers an await: the one running (the coroutine's owner), else the
   * emitter itself when it is a Kanama script.
   */
  private fun awaitRouter(): GodotObject {
    val running = WebFrameScheduler.currentOwnerOrZero()
    if (running > 0) return GodotObject(WebObjectId(running))
    check(webScriptInstance(emitter.handle.value) != null) {
      "signal $name: await needs a running Kanama script (call it from a script callback or coroutine)"
    }
    return emitter
  }

  override fun toString(): String = "Signal($name)"
}

private val NO_FLAGS = GodotObject.ConnectFlags(0L)

/** A signal without arguments. */
class Signal0(emitter: GodotObject, name: String) : TypedSignal(emitter, name) {
  /** Connects [callback], bound to [target]. */
  fun connect(target: GodotObject, callback: () -> Unit): SignalConnection = connect(target, NO_FLAGS, callback)

  /** Connects [callback], bound to [target], with [flags]. */
  fun connect(target: GodotObject, flags: GodotObject.ConnectFlags, callback: () -> Unit): SignalConnection =
    connectArgs(target, flags) { callback() }

  /** Suspends until the signal fires. */
  suspend fun await() {
    awaitArgs {}
  }

  /** Emits the signal. */
  fun emit() {
    emitter.emitSignal(name)
  }
}

/** A signal with one argument. */
class Signal1<A>(emitter: GodotObject, name: String, private val a: SignalArgType<A>) : TypedSignal(emitter, name) {
  /** Connects [callback], bound to [target]. */
  fun connect(target: GodotObject, callback: (A) -> Unit): SignalConnection = connect(target, NO_FLAGS, callback)

  /** Connects [callback], bound to [target], with [flags]. */
  fun connect(target: GodotObject, flags: GodotObject.ConnectFlags, callback: (A) -> Unit): SignalConnection =
    connectArgs(target, flags) { args -> callback(a.read(args, 0)) }

  /** Suspends until the signal fires and returns its argument. */
  suspend fun await(): A = awaitArgs { args -> a.read(args, 0) }

  /** Emits the signal. */
  fun emit(a: A) {
    emitter.emitSignal(name, this.a.write(a))
  }
}

/** A signal with two arguments; [await] returns a [SignalArgs2] to destructure. */
class Signal2<A, B>(
  emitter: GodotObject,
  name: String,
  private val a: SignalArgType<A>,
  private val b: SignalArgType<B>,
) : TypedSignal(emitter, name) {
  /** Connects [callback], bound to [target]. */
  fun connect(target: GodotObject, callback: (A, B) -> Unit): SignalConnection = connect(target, NO_FLAGS, callback)

  /** Connects [callback], bound to [target], with [flags]. */
  fun connect(target: GodotObject, flags: GodotObject.ConnectFlags, callback: (A, B) -> Unit): SignalConnection =
    connectArgs(target, flags) { args -> callback(a.read(args, 0), b.read(args, 1)) }

  /** Suspends until the signal fires: `val (first, second) = signal.await()`. */
  suspend fun await(): SignalArgs2<A, B> = awaitArgs { args -> SignalArgs2(a.read(args, 0), b.read(args, 1)) }

  /** Emits the signal. */
  fun emit(a: A, b: B) {
    emitter.emitSignal(name, this.a.write(a), this.b.write(b))
  }
}

/** A signal with three arguments. */
class Signal3<A, B, C>(
  emitter: GodotObject,
  name: String,
  private val a: SignalArgType<A>,
  private val b: SignalArgType<B>,
  private val c: SignalArgType<C>,
) : TypedSignal(emitter, name) {
  /** Connects [callback], bound to [target]. */
  fun connect(target: GodotObject, callback: (A, B, C) -> Unit): SignalConnection = connect(target, NO_FLAGS, callback)

  /** Connects [callback], bound to [target], with [flags]. */
  fun connect(target: GodotObject, flags: GodotObject.ConnectFlags, callback: (A, B, C) -> Unit): SignalConnection =
    connectArgs(target, flags) { args -> callback(a.read(args, 0), b.read(args, 1), c.read(args, 2)) }

  /** Suspends until the signal fires: `val (x, y, z) = signal.await()`. */
  suspend fun await(): SignalArgs3<A, B, C> =
    awaitArgs { args -> SignalArgs3(a.read(args, 0), b.read(args, 1), c.read(args, 2)) }

  /** Emits the signal. */
  fun emit(a: A, b: B, c: C) {
    emitter.emitSignal(name, this.a.write(a), this.b.write(b), this.c.write(c))
  }
}

/** A signal with four arguments. */
class Signal4<A, B, C, D>(
  emitter: GodotObject,
  name: String,
  private val a: SignalArgType<A>,
  private val b: SignalArgType<B>,
  private val c: SignalArgType<C>,
  private val d: SignalArgType<D>,
) : TypedSignal(emitter, name) {
  /** Connects [callback], bound to [target]. */
  fun connect(target: GodotObject, callback: (A, B, C, D) -> Unit): SignalConnection =
    connect(target, NO_FLAGS, callback)

  /** Connects [callback], bound to [target], with [flags]. */
  fun connect(target: GodotObject, flags: GodotObject.ConnectFlags, callback: (A, B, C, D) -> Unit): SignalConnection =
    connectArgs(target, flags) { args ->
      callback(a.read(args, 0), b.read(args, 1), c.read(args, 2), d.read(args, 3))
    }

  /** Suspends until the signal fires: `val (a, b, c, d) = signal.await()`. */
  suspend fun await(): SignalArgs4<A, B, C, D> =
    awaitArgs { args -> SignalArgs4(a.read(args, 0), b.read(args, 1), c.read(args, 2), d.read(args, 3)) }

  /** Emits the signal. */
  fun emit(a: A, b: B, c: C, d: D) {
    emitter.emitSignal(name, this.a.write(a), this.b.write(b), this.c.write(c), this.d.write(d))
  }
}

/** A signal with five arguments. */
class Signal5<A, B, C, D, E>(
  emitter: GodotObject,
  name: String,
  private val a: SignalArgType<A>,
  private val b: SignalArgType<B>,
  private val c: SignalArgType<C>,
  private val d: SignalArgType<D>,
  private val e: SignalArgType<E>,
) : TypedSignal(emitter, name) {
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
    emitter.emitSignal(name, this.a.write(a), this.b.write(b), this.c.write(c), this.d.write(d), this.e.write(e))
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

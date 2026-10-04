package net.multigesture.kanama.api

import kotlin.coroutines.resume
import kotlin.reflect.KClass
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3

/** Wraps an emitted object handle in its Kotlin wrapper class. */
fun interface SignalObjectWrapper<out T> {
  fun wrap(handle: GodotHandle): T?
}

/**
 * The Web counterpart of the native `SignalArgType` (task 134 D4): how a typed signal's argument
 * arrives. The Web bridge delivers at most one argument, as an object handle or a packed scalar
 * (`int`, `float`, `bool`, `String`/`StringName`, `Vector2`, `Vector2i`, `Vector3`, a Godot enum);
 * any other type throws [UnsupportedOperationException] when connected, never a silent default.
 */
abstract class SignalArgType<T> internal constructor(
  /** The Godot type name (`"int"`, `"Node3D"`). */
  val godotType: String
) {
  /** Connects [callback] on the bridge path for this type. */
  internal abstract fun connect(
    signal: GodotSignal,
    target: GodotObject,
    flags: GodotObject.ConnectFlags,
    callback: (T) -> Unit,
  ): SignalConnection

  /** The value handed to `emit_signal`. */
  internal open fun write(value: T): Any? = value

  override fun toString(): String = "SignalArgType($godotType)"

  private class Scalar<T>(godotType: String, private val parse: (String) -> T) : SignalArgType<T>(godotType) {
    override fun connect(
      signal: GodotSignal,
      target: GodotObject,
      flags: GodotObject.ConnectFlags,
      callback: (T) -> Unit,
    ): SignalConnection = signal.connectScalarConnection(target, flags, parse, callback)
  }

  companion object {
    /** Godot `int`. */
    val LONG: SignalArgType<Long> = Scalar("int") { it.trim().toLong() }

    /** Godot `float`. */
    val DOUBLE: SignalArgType<Double> = Scalar("float") { it.trim().toDouble() }

    /** Godot `bool`. */
    val BOOLEAN: SignalArgType<Boolean> = Scalar("bool") { it == "1" }

    /** Godot `String` or `StringName`. */
    val STRING: SignalArgType<String> = Scalar("String") { it }

    /** An object argument wrapped by [wrap]; a null object throws (use [nullableObjectOf]). */
    fun <T : Any> objectOf(godotType: String, wrap: SignalObjectWrapper<T>): SignalArgType<T> =
      object : SignalArgType<T>(godotType) {
        override fun connect(
          signal: GodotSignal,
          target: GodotObject,
          flags: GodotObject.ConnectFlags,
          callback: (T) -> Unit,
        ): SignalConnection =
          signal.connectObjectConnection(target, flags) { handle ->
            val value =
              handle?.let { wrap.wrap(it) }
                ?: throw IllegalArgumentException("signal '${signal.name}': expected $godotType, got null")
            callback(value)
          }
      }

    /** An object argument that may be `null`. */
    fun <T : Any> nullableObjectOf(godotType: String, wrap: SignalObjectWrapper<T>): SignalArgType<T?> =
      object : SignalArgType<T?>(godotType) {
        override fun connect(
          signal: GodotSignal,
          target: GodotObject,
          flags: GodotObject.ConnectFlags,
          callback: (T?) -> Unit,
        ): SignalConnection = signal.connectObjectConnection(target, flags) { handle -> callback(handle?.let { wrap.wrap(it) }) }
      }

    /** A Godot enum or bitfield carried as `int`. */
    fun <E> enumOf(godotType: String, wrap: (Long) -> E, raw: (E) -> Long): SignalArgType<E> =
      object : SignalArgType<E>(godotType) {
        override fun connect(
          signal: GodotSignal,
          target: GodotObject,
          flags: GodotObject.ConnectFlags,
          callback: (E) -> Unit,
        ): SignalConnection = signal.connectScalarConnection(target, flags, { wrap(it.trim().toLong()) }, callback)

        override fun write(value: E): Any? = raw(value)
      }

    /** A value type: `Vector2`, `Vector2i` and `Vector3` are delivered on Web; others throw on connect. */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> valueOf(godotType: String, type: KClass<*>): SignalArgType<T> =
      when (type) {
        Vector2::class -> Scalar(godotType) { packed -> GodotSignal.parseVector2Packed(packed) as T }
        Vector2i::class -> Scalar(godotType) { packed -> GodotSignal.parseVector2iPacked(packed) as T }
        Vector3::class -> Scalar(godotType) { packed -> GodotSignal.parseVector3Packed(packed) as T }
        else ->
          object : SignalArgType<T>(godotType) {
            override fun connect(
              signal: GodotSignal,
              target: GodotObject,
              flags: GodotObject.ConnectFlags,
              callback: (T) -> Unit,
            ): SignalConnection =
              throw UnsupportedOperationException(
                "signal '${signal.name}': a $godotType argument is not delivered on Web yet"
              )
          }
      }
  }
}

/**
 * A named signal on the object that emits it, typed (task 134 D4); see the native `TypedSignal`.
 * On Web the bridge delivers signals with no or one argument, so only [Signal0] and [Signal1] exist
 * here.
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

  internal suspend fun <R> awaitWith(connect: (GodotObject.ConnectFlags, (R) -> Unit) -> SignalConnection): R =
    suspendCancellableCoroutine { continuation ->
      val connection =
        connect(GodotObject.ConnectFlags.ONE_SHOT) { value ->
          if (continuation.isActive) continuation.resume(value)
        }
      if (connection.error != GodotError.OK) {
        continuation.cancel(CancellationException("connect($name) failed: error=${connection.error}"))
      } else {
        continuation.invokeOnCancellation { connection.close() }
      }
    }

  override fun toString(): String = "Signal($name)"
}

/** A signal without arguments. */
class Signal0(emitter: GodotObject, name: String) : TypedSignal(emitter, name) {
  /** Connects [callback], bound to [target]. */
  fun connect(target: GodotObject, callback: () -> Unit): SignalConnection =
    connect(target, GodotObject.ConnectFlags(0L), callback)

  /** Connects [callback], bound to [target], with [flags]. */
  fun connect(target: GodotObject, flags: GodotObject.ConnectFlags, callback: () -> Unit): SignalConnection =
    emitter.signal(name).connectPlainConnection(target, 0, flags, callback)

  /** Suspends until the signal fires. */
  suspend fun await() {
    awaitWith<Unit> { flags, resume -> emitter.signal(name).connectPlainConnection(emitter, 0, flags) { resume(Unit) } }
  }

  /** Emits the signal. */
  fun emit() {
    emitter.emitSignal(name)
  }
}

/** A signal with one argument. */
class Signal1<A>(emitter: GodotObject, name: String, private val a: SignalArgType<A>) : TypedSignal(emitter, name) {
  /** Connects [callback], bound to [target]. */
  fun connect(target: GodotObject, callback: (A) -> Unit): SignalConnection =
    connect(target, GodotObject.ConnectFlags(0L), callback)

  /** Connects [callback], bound to [target], with [flags]. */
  fun connect(target: GodotObject, flags: GodotObject.ConnectFlags, callback: (A) -> Unit): SignalConnection =
    a.connect(emitter.signal(name), target, flags, callback)

  /** Suspends until the signal fires and returns its argument. */
  suspend fun await(): A = awaitWith { flags, resume -> a.connect(emitter.signal(name), emitter, flags, resume) }

  /** Emits the signal. */
  fun emit(a: A) {
    emitter.emitSignal(name, this.a.write(a))
  }
}

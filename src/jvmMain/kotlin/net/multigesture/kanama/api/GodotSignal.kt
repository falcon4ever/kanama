package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.SignalCallbackRegistry
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * Small Kotlin-facing handle for a named Godot signal on an object — the desktop/Android actual of
 * `src/commonMain/.../api/GodotSignal.expect.kt` (task 117 P4′, D25).
 *
 * This is intentionally a thin Callable-based bridge. It connects a signal to
 * a method on another Godot object or Kanama script instance, which keeps
 * lifetime ownership in Godot; lambda connections go through [SignalCallbackRegistry]
 * and a bound Callable. The overloads replace default arguments (D24).
 */
actual class GodotSignal
internal actual constructor(
    internal val owner: GodotObject,
    actual val name: String,
) {
    actual fun connect(target: GodotObject, method: String): Long =
        connect(target, method, GodotObject.CONNECT_DEFAULT)

    actual fun connect(target: GodotObject, method: String, flags: Long): Long =
        owner.connect(name, target, method, flags)

    actual fun disconnect(target: GodotObject, method: String) {
        owner.disconnect(name, target, method)
    }

    actual fun emit(vararg args: Any?) {
        owner.emitSignal(name, *args)
    }

    actual fun connect(
        target: GodotObject,
        argumentCount: Int,
        callback: (List<Any?>) -> Unit,
    ): SignalConnection = connect(target, argumentCount, GodotObject.CONNECT_DEFAULT, callback)

    actual fun connect(
        target: GodotObject,
        argumentCount: Int,
        flags: Long,
        callback: (List<Any?>) -> Unit,
    ): SignalConnection {
        require(argumentCount in 0..3) { "Signal lambda callbacks currently support 0..3 emitted arguments" }
        val id = SignalCallbackRegistry.register(callback)
        val method = "__kanama_signal_dispatch$argumentCount"
        val boundArgs = listOf(id)
        val error = owner.connectBound(name, target, method, boundArgs, flags)
        if (error != 0L) {
            SignalCallbackRegistry.unregister(id)
        }
        return SignalConnection(
            owner = owner,
            signal = name,
            target = target,
            method = method,
            boundArgs = boundArgs,
            callbackId = id,
            error = error,
            disconnectOnClose = flags and GodotObject.CONNECT_ONE_SHOT == 0L,
        )
    }

    actual fun connectObject(target: GodotObject, callback: (GodotObject) -> Unit): SignalConnection =
        connectObject(target, GodotObject.CONNECT_DEFAULT, callback)

    actual fun connectObject(
        target: GodotObject,
        flags: Long,
        callback: (GodotObject) -> Unit,
    ): SignalConnection =
        connect(target, argumentCount = 1, flags = flags) { args ->
            (args.firstOrNull() as? GodotObject)?.let(callback)
        }

    actual suspend fun await(target: GodotObject): List<Any?> = await(target, argumentCount = 0)

    actual suspend fun await(target: GodotObject, argumentCount: Int): List<Any?> =
        suspendCancellableCoroutine { continuation ->
            var connection: SignalConnection? = null
            connection = connect(target, argumentCount, GodotObject.CONNECT_ONE_SHOT) { args ->
                connection?.close()
                if (continuation.isActive) continuation.resume(args)
            }
            if (connection.error != 0L) {
                connection.close()
                continuation.cancel(CancellationException("connect($name) failed: error=${connection.error}"))
            } else {
                continuation.invokeOnCancellation {
                    connection.close()
                }
            }
        }

    actual suspend fun awaitObject(target: GodotObject): GodotObject? =
        await(target, argumentCount = 1).firstOrNull() as? GodotObject
}

actual class SignalConnection internal constructor(
    private val owner: GodotObject,
    private val signal: String,
    private val target: GodotObject,
    private val method: String,
    private val boundArgs: List<Any?>,
    private val callbackId: Long,
    actual val error: Long,
    private val disconnectOnClose: Boolean,
) : AutoCloseable {
    private var closed = false

    actual override fun close() {
        if (closed) return
        closed = true
        SignalCallbackRegistry.unregister(callbackId)
        if (error == 0L && disconnectOnClose) {
            owner.disconnectBound(signal, target, method, boundArgs)
        }
    }
}

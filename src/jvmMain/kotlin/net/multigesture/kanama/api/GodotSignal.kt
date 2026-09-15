package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.SignalCallbackRegistry
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * Small Kotlin-facing handle for a named Godot signal on an object.
 *
 * This is intentionally a thin Callable-based bridge. It connects a signal to
 * a method on another Godot object or Kanama script instance, which keeps
 * lifetime ownership in Godot until a later Kotlin lambda registry exists.
 */
class GodotSignal internal constructor(
    internal val owner: GodotObject,
    val name: String,
) {
    fun connect(target: GodotObject, method: String, flags: Long = GodotObject.CONNECT_DEFAULT): Long =
        owner.connect(name, target, method, flags)

    fun disconnect(target: GodotObject, method: String) {
        owner.disconnect(name, target, method)
    }

    fun emit(vararg args: Any?) {
        owner.emitSignal(name, *args)
    }

    fun connect(
        target: GodotObject,
        argumentCount: Int,
        flags: Long = GodotObject.CONNECT_DEFAULT,
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

    fun connectObject(
        target: GodotObject,
        flags: Long = GodotObject.CONNECT_DEFAULT,
        callback: (GodotObject) -> Unit,
    ): SignalConnection =
        connect(target, argumentCount = 1, flags = flags) { args ->
            (args.firstOrNull() as? GodotObject)?.let(callback)
        }

    suspend fun await(target: GodotObject, argumentCount: Int = 0): List<Any?> =
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

    suspend fun awaitObject(target: GodotObject): GodotObject? =
        await(target, argumentCount = 1).firstOrNull() as? GodotObject
}

class SignalConnection internal constructor(
    private val owner: GodotObject,
    private val signal: String,
    private val target: GodotObject,
    private val method: String,
    private val boundArgs: List<Any?>,
    private val callbackId: Long,
    val error: Long,
    private val disconnectOnClose: Boolean,
) : AutoCloseable {
    private var closed = false

    override fun close() {
        if (closed) return
        closed = true
        SignalCallbackRegistry.unregister(callbackId)
        if (error == 0L && disconnectOnClose) {
            owner.disconnectBound(signal, target, method, boundArgs)
        }
    }
}

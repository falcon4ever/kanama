package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.SignalCallables
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
 * and a custom Callable ([SignalCallables]) whose free_func releases the closure. The overloads
 * replace default arguments (D24).
 */
actual class GodotSignal
internal actual constructor(
    internal val owner: GodotObject,
    actual val name: String,
) {
    actual fun connect(target: GodotObject, method: String): GodotError =
        connect(target, method, GodotObject.ConnectFlags(0L))

    actual fun connect(target: GodotObject, method: String, flags: GodotObject.ConnectFlags): GodotError =
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
    ): SignalConnection = connect(target, argumentCount, GodotObject.ConnectFlags(0L), callback)

    actual fun connect(
        target: GodotObject,
        argumentCount: Int,
        flags: GodotObject.ConnectFlags,
        callback: (List<Any?>) -> Unit,
    ): SignalConnection {
        require(argumentCount >= 0) { "argumentCount must not be negative" }
        return connectArgs(target, argumentCount, flags, null, SignalCallbackRegistry.listDispatch(argumentCount, callback))
    }

    internal actual fun connectArgs(
        target: GodotObject,
        argumentCount: Int,
        flags: GodotObject.ConnectFlags,
        onRelease: (() -> Unit)?,
        dispatch: (SignalArgReader) -> Unit,
    ): SignalConnection {
        val oneShot = GodotObject.ConnectFlags.ONE_SHOT in flags
        val id = SignalCallbackRegistry.register(argumentCount, onRelease, dispatch)
        // A custom Callable bound to the receiver: Godot calls its free_func -- releasing the
        // closure -- whenever it drops the connection (receiver or emitter freed, one-shot fired,
        // disconnected, failed connect). Task 131; the iOS shim works the same way.
        val error = GodotError(SignalCallables.connect(owner.segment, name, target.instanceId, id, flags.value))
        if (error == GodotError.OK) {
            // Task 133 C2: a hot reload disconnects the lambdas a re-created autoload made.
            SignalCallbackRegistry.noteConnection(
                id,
                SignalCallbackRegistry.Connection(owner.segment.address(), owner.instanceId, name, target.instanceId),
            )
        }
        return SignalConnection(
            owner = owner,
            signal = name,
            receiverInstanceId = target.instanceId,
            callbackId = id,
            error = error,
            oneShot = oneShot,
        )
    }

    actual fun connectObject(target: GodotObject, callback: (GodotObject) -> Unit): SignalConnection =
        connectObject(target, GodotObject.ConnectFlags(0L), callback)

    actual fun connectObject(
        target: GodotObject,
        flags: GodotObject.ConnectFlags,
        callback: (GodotObject) -> Unit,
    ): SignalConnection =
        connect(target, argumentCount = 1, flags = flags) { args ->
            (args.firstOrNull() as? GodotObject)?.let(callback)
        }

    actual suspend fun await(target: GodotObject): List<Any?> = await(target, argumentCount = 0)

    actual suspend fun await(target: GodotObject, argumentCount: Int): List<Any?> =
        suspendCancellableCoroutine { continuation ->
            var connection: SignalConnection? = null
            connection = connect(target, argumentCount, GodotObject.ConnectFlags.ONE_SHOT) { args ->
                connection?.close()
                if (continuation.isActive) continuation.resume(args)
            }
            if (connection.error != GodotError.OK) {
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
    private val receiverInstanceId: Long,
    private val callbackId: Long,
    actual val error: GodotError,
    private val oneShot: Boolean,
) : AutoCloseable {
    @Volatile private var closed = false

    /**
     * Disconnects, whatever the flags (task 134 C review S3: a closed `ONE_SHOT` connection that had
     * not fired, or a cancelled `await`, used to stay connected). Nothing to do when Godot already
     * dropped the Callable (its `free_func` released the entry: fired one-shot, receiver or emitter
     * freed) or the emitter is gone. Godot is asked `is_connected` first only for a one-shot
     * connection, which Godot disconnects before calling while the entry lives on until the
     * emission ends.
     */
    actual override fun close() {
        if (closed) return
        closed = true
        val live = SignalCallbackRegistry.entry(callbackId) != null
        SignalCallbackRegistry.unregister(callbackId)
        if (error != GodotError.OK || !live || !owner.isAlive(owner.segment)) return
        SignalCallables.disconnect(owner.segment, signal, receiverInstanceId, callbackId, checkConnected = oneShot)
    }
}

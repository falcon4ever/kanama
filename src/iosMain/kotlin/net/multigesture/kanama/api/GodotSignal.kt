package net.multigesture.kanama.api

import kotlinx.coroutines.CompletableDeferred

// KANAMA-IOS-HANDWRITTEN: [runtime] signal/connect/emitSignal/await use the custom GDExtension
// Callable + IosCallableRegistry (lambda/bound dispatch); bespoke runtime, not generated.
/**
 * Small Kotlin-facing handle for a named Godot signal on an object — the iOS implementation.
 *
 * One of the two genuinely per-platform classes left among the roots (task 117 P3′, D21): desktop
 * dispatches lambda connections through `SignalCallbackRegistry` and a bound Callable, iOS through
 * [IosCallableRegistry] and a custom Callable built in the C shim. The public surface is desktop's
 * (`src/jvmMain/.../api/GodotSignal.kt`), held by `scripts/check_wrapper_parity.py`.
 */
class GodotSignal internal constructor(
    internal val owner: GodotObject,
    val name: String,
) {
    fun connect(target: GodotObject, method: String, flags: Long = GodotObject.CONNECT_DEFAULT): Long =
        owner.connect(name, target, method, flags)

    // Object.disconnect(signal, Callable(target, method)) — symmetric to connect(target, method).
    fun disconnect(target: GodotObject, method: String) {
        owner.disconnect(name, target, method)
    }

    /** Emits this signal (matches desktop Signal.emit). Delegates to the owner's emit_signal path. */
    fun emit(vararg args: Any?) {
        owner.emitSignal(name, *args)
    }

    fun connect(
        target: GodotObject,
        argumentCount: Int,
        flags: Long = GodotObject.CONNECT_DEFAULT,
        callback: (List<Any?>) -> Unit,
    ): SignalConnection {
        val callbackId = IosCallableRegistry.register(callback)
        // Pass the receiver (target) so the Callable is bound to its ObjectID and Godot auto-disconnects
        // it when the receiver is freed. Previously target was ignored, leaving an object-less Callable
        // that survived the receiver's free and fired into freed memory on later emissions.
        val result = IosGodot.objectConnectCallable(owner.segment.address(), name, target.segment.address(), callbackId, flags)
        if (result != 0L) {
            // connect failed; Godot freed the callable (which released the entry),
            // but release defensively in case it never reached the trampoline path.
            IosCallableRegistry.release(callbackId)
        }
        return SignalConnection(result, owner, name, callbackId, target)
    }

    fun connectObject(
        target: GodotObject,
        flags: Long = GodotObject.CONNECT_DEFAULT,
        callback: (GodotObject) -> Unit,
    ): SignalConnection =
        connect(target, argumentCount = 1, flags = flags) { args ->
            (args.firstOrNull() as? GodotObject)?.let(callback)
        }

    suspend fun await(target: GodotObject, argumentCount: Int = 0): List<Any?> {
        // Connect a one-shot callable that completes the deferred when the signal
        // fires, then suspend until then. CONNECT_ONE_SHOT makes Godot drop the
        // connection after it fires, which releases the registry entry via free_func.
        val deferred = CompletableDeferred<List<Any?>>()
        connect(target, argumentCount, GodotObject.CONNECT_ONE_SHOT) { args ->
            deferred.complete(args)
        }
        return deferred.await()
    }

    // Desktop's awaitObject, over the one-shot [await] above (task 117 P3′, D21).
    suspend fun awaitObject(target: GodotObject): GodotObject? =
        await(target, argumentCount = 1).firstOrNull() as? GodotObject
}

class SignalConnection internal constructor(
    // Real Object.connect return Error (0 == OK) from the lambda-connect path.
    val error: Long = 0L,
    private val owner: GodotObject? = null,
    private val signalName: String = "",
    private val callbackId: Long = 0L,
    // The receiver the Callable was bound to at connect time; disconnect must present the same
    // receiver so Godot also erases the receiver-side connection entry (task 108).
    private val target: GodotObject? = null,
) : AutoCloseable {
    private var closed = false

    // Disconnect the lambda Callable. The C path recreates the identity-equal custom Callable
    // (call_func + callback_id, bound to the same receiver) and Object.disconnects it; the
    // connection's free_func then releases the registry entry. No-op if the connect failed or
    // close() was already called. (A CONNECT_ONE_SHOT connection auto-disconnects when it fires;
    // calling close() afterwards is a benign redundant disconnect.) Phase 4.1b.
    override fun close() {
        if (closed || error != 0L || owner == null || callbackId == 0L) {
            return
        }
        closed = true
        IosGodot.objectDisconnectCallable(
            owner.segment.address(),
            signalName,
            target?.segment?.address() ?: 0L,
            callbackId,
        )
    }
}

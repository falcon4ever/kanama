package net.multigesture.kanama.api

/**
 * Small Kotlin-facing handle for a named Godot signal on an object (`GodotObject.signal(name)`).
 *
 * One of the two genuinely per-platform classes among the roots (task 117 D21/D25): desktop and
 * Android dispatch lambda connections through `SignalCallbackRegistry` and a bound Callable
 * (`src/jvmMain/.../api/GodotSignal.kt`), iOS through `IosCallableRegistry` and a custom Callable
 * built in the C shim (`src/iosMain/.../api/GodotSignal.kt`). This `expect` is the public surface
 * both must implement — the compiler is the contract (it replaced `scripts/check_wrapper_parity.py`).
 *
 * No default arguments (D24): the Android lane skips `*.expect.kt` and strips `actual`, so a default
 * declared only here would not exist there. Every omitted-argument form is its own overload, which
 * keeps positional, named and trailing-lambda call sites compiling: `flags` defaults to
 * `GodotObject.ConnectFlags(0L)` (Godot names no zero flag) and `argumentCount` (of [await]) to 0.
 */
expect class GodotSignal internal constructor(owner: GodotObject, name: String) {
    /** The signal name. */
    val name: String

    /** `connect(target, method, GodotObject.ConnectFlags(0L))`. */
    fun connect(target: GodotObject, method: String): GodotError

    /** Connects this signal to [method] on [target]; returns Godot's `Error` ([GodotError.OK] on success). */
    fun connect(target: GodotObject, method: String, flags: GodotObject.ConnectFlags): GodotError

    /** Disconnects the [target]/[method] connection made by [connect]. */
    fun disconnect(target: GodotObject, method: String)

    /** Emits this signal with [args]. */
    fun emit(vararg args: Any?)

    /** `connect(target, argumentCount, GodotObject.ConnectFlags(0L), callback)`. */
    fun connect(
        target: GodotObject,
        argumentCount: Int,
        callback: (List<Any?>) -> Unit,
    ): SignalConnection

    /**
     * Connects a Kotlin lambda receiving the signal's first [argumentCount] arguments, bound to
     * [target] so Godot drops the connection when [target] is freed. Close the returned
     * [SignalConnection] to disconnect.
     */
    fun connect(
        target: GodotObject,
        argumentCount: Int,
        flags: GodotObject.ConnectFlags,
        callback: (List<Any?>) -> Unit,
    ): SignalConnection

    /** `connectObject(target, GodotObject.ConnectFlags(0L), callback)`. */
    fun connectObject(target: GodotObject, callback: (GodotObject) -> Unit): SignalConnection

    /** Connects a lambda receiving the signal's first argument as a [GodotObject]. */
    fun connectObject(
        target: GodotObject,
        flags: GodotObject.ConnectFlags,
        callback: (GodotObject) -> Unit,
    ): SignalConnection

    /** `await(target, 0)`. */
    suspend fun await(target: GodotObject): List<Any?>

    /** Suspends until the signal fires once; returns its first [argumentCount] arguments. */
    suspend fun await(target: GodotObject, argumentCount: Int): List<Any?>

    /** Suspends until the signal fires once; returns its first argument as a [GodotObject]. */
    suspend fun awaitObject(target: GodotObject): GodotObject?
}

/**
 * A lambda connection made by [GodotSignal.connect] / [GodotSignal.connectObject]; [close]
 * disconnects it. Each platform's `actual` keeps its own internal constructor (its registry id
 * and the receiver it must present again to disconnect), so the `expect` declares none (D25).
 */
expect class SignalConnection : AutoCloseable {
    /** Godot's `Error` from the connect call ([GodotError.OK] on success). */
    val error: GodotError

    override fun close()
}

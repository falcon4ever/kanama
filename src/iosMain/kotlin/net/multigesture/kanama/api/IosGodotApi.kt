@file:Suppress("unused")

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.* // generated ObjectCalls.* extension helpers
import kotlin.experimental.ExperimentalNativeApi
import kotlin.jvm.JvmName
import kotlin.native.CName
import kotlin.native.concurrent.ThreadLocal
import net.multigesture.kanama.ios.IosSignalArgReader
import kotlinx.cinterop.COpaquePointerVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import net.multigesture.kanama.binding.runtime.IosScriptErrors
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.get
import kotlinx.cinterop.value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_connect
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_connect_callable
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_disconnect_callable
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_emit_signal_int
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_emit_signal_vector2i
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_is_instance_id_valid
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_get_instance_id
import kotlin.coroutines.CoroutineContext

/**
 * Deprecated, no longer applied to any Kanama API (task 97; the generated iOS `RefCounted.close()`
 * dropped it in task 103). Kept so an existing `@OptIn(ManualGodotLifetimeApi::class)` still
 * compiles; delete the opt-in, nothing replaces it.
 */
@Deprecated("No longer required; close() is the documented contract (docs/game-dev/godot-api.md#resource-ownership)")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class ManualGodotLifetimeApi

// KanamaScript is common code since task 133 (src/commonMain/.../api/KanamaScript.kt).

// KANAMA-IOS-HANDWRITTEN: [platform] KanamaScope bridges Godot's main thread to Kotlin coroutines; not generatable from extension_api.json.
// An exception escaping a coroutine is reported as a Godot script error with the game's file:line
// (task 131 item 10), like desktop; before, the default handler terminated the app.
// Internal since task 133: it backs KanamaScript.scriptScope (ScriptRuntime.newScriptScope).
internal class KanamaScope : CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext =
        Dispatchers.Main + job + kotlinx.coroutines.CoroutineExceptionHandler { context, throwable ->
            val name = context[kotlinx.coroutines.CoroutineName]?.name
            IosScriptErrors.report(
                throwable,
                if (name.isNullOrEmpty()) "KanamaScope coroutine" else "KanamaScope coroutine $name",
            )
        }

    fun cancel() {
        job.cancel()
    }
}

inline fun <reified T> GodotObject.kotlinScriptInstance(): T? =
    net.multigesture.kanama.ios.iosScriptInstanceForOwner(handle.segment.address()) as? T

inline fun <reified T> Node.kotlinScriptInstance(): T? =
    GodotObject(handle).kotlinScriptInstance<T>()

@OptIn(ExperimentalForeignApi::class)
// KANAMA-IOS-HANDWRITTEN: [seam] thin cinterop facade over the C shim entry points the runtime and
// the generated tree's iOS bodies still call (instance ids, signal connections).
// Task 129 A removed the 53 functions nothing called any more; task 129 C the typed-load and
// AudioStreamPlayer ones (both classes are generated once now).
internal object IosGodot {
    fun objectGetInstanceId(objectHandle: Long): Long =
        kanama_ios_godot_object_get_instance_id(objectHandle)

    fun isInstanceIdValid(instanceId: Long): Boolean =
        kanama_ios_godot_is_instance_id_valid(instanceId) != 0

    fun objectIsLive(objectHandle: Long, instanceId: Long): Boolean =
        net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_is_live(objectHandle, instanceId) != 0

    fun objectEmitSignalInt(objectHandle: Long, signalName: String, value: Long): Int =
        kanama_ios_godot_object_emit_signal_int(objectHandle, signalName, value)

    fun objectEmitSignalVector2i(objectHandle: Long, signalName: String, x: Long, y: Long): Int =
        kanama_ios_godot_object_emit_signal_vector2i(objectHandle, signalName, x, y)

    fun objectConnect(sourceObject: Long, signalName: String, targetObject: Long, method: String, flags: Long): Long =
        kanama_ios_godot_object_connect(sourceObject, signalName, targetObject, method, flags)

    fun objectConnectCallable(sourceObject: Long, signalName: String, targetObject: Long, callbackId: Long, flags: Long): Long =
        kanama_ios_godot_object_connect_callable(sourceObject, signalName, targetObject, callbackId, flags)

    fun objectDisconnectCallable(sourceObject: Long, signalName: String, targetObject: Long, callbackId: Long): Int =
        kanama_ios_godot_object_disconnect_callable(sourceObject, signalName, targetObject, callbackId)
}


// Registry backing lambda/bound signal connections. A connection registers its
// callback here and passes the integer id to the C shim, which binds it to a
// custom Godot Callable. When the signal fires the shim calls back into
// kanamaIosRuntimeDispatchCallable with the arguments as PT-tagged cells; when Godot drops the
// connection it calls kanamaIosRuntimeReleaseCallable so the entry can be collected. Since task 134
// D4 an entry reads its arguments through a [SignalArgReader] (typed signals, no argument cap).
internal object IosCallableRegistry {
    class Entry(
        val argumentCount: Int,
        val onRelease: (() -> Unit)?,
        val dispatch: (SignalArgReader) -> Unit,
    )

    private var nextId = 1L
    private val callbacks = HashMap<Long, Entry>()

    fun register(argumentCount: Int, onRelease: (() -> Unit)?, dispatch: (SignalArgReader) -> Unit): Long {
        val id = nextId++
        callbacks[id] = Entry(argumentCount, onRelease, dispatch)
        return id
    }

    /** Registers [callback] with every emitted argument decoded (the self-test's lambda rows). */
    fun register(callback: (List<Any?>) -> Unit): Long =
        register(0, null) { args -> callback(List(args.count) { args.value(it) }) }

    fun entry(callbackId: Long): Entry? = callbacks[callbackId]

    /** Godot dropped the Callable (its free_func): drops the entry and runs its release hook. */
    fun release(callbackId: Long) {
        callbacks.remove(callbackId)?.onRelease?.invoke()
    }

    /** Live entries; the self-test's leak rows compare it before and after (task 131). */
    val size: Int
        get() = callbacks.size
}

@ThreadLocal
private object IosSignalReaders {
    val reader = IosSignalArgReader()
}

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_dispatch_callable")
fun kanamaIosRuntimeDispatchCallable(
    callbackId: Long,
    argumentTags: CPointer<IntVar>?,
    argumentPtrs: CPointer<COpaquePointerVar>?,
    argumentCount: Int,
): Int {
    val entry = IosCallableRegistry.entry(callbackId) ?: return 0
    // Fewer arguments than the connection reads: a call error, as for a GDScript function.
    if (argumentCount < entry.argumentCount) return entry.argumentCount
    // A throwing signal lambda is contained here (task 131): an exception crossing this @CName
    // export terminates the app. It is printed and reported as a Godot script error; the Callable
    // call itself still completes, as a GDScript lambda's runtime error does.
    try {
        IosSignalReaders.reader.dispatch(argumentTags, argumentPtrs, argumentCount, entry.dispatch)
    } catch (t: Throwable) {
        IosScriptErrors.report(t, "signal lambda")
    }
    return 0
}

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_release_callable")
fun kanamaIosRuntimeReleaseCallable(callbackId: Long) {
    IosCallableRegistry.release(callbackId)
}

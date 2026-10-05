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
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_play
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_set_bus
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_set_pitch_scale
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_set_stream
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_set_stream_paused
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_set_volume_db
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_construct_object
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_connect
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_connect_callable
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_disconnect_callable
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_emit_signal_int
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_emit_signal_vector2i
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_is_instance_id_valid
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_get_instance_id
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_resource_loader_load
import kotlin.coroutines.CoroutineContext
import kotlin.math.PI
import kotlin.math.pow
import kotlin.random.Random

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

class AudioStreamPlayer(handle: GodotHandle) : Node(handle) {
    // ===== BEGIN GENERATED ENUMS: AudioStreamPlayer (scripts/generate_api_wrapper.py — do not edit) =====
    /**
     * Godot's `AudioStreamPlayer.MixTarget` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`AudioStreamPlayer.MixTarget.<NAME>`).
     *
     * Generated from Godot docs: AudioStreamPlayer.MixTarget
     */
    value class MixTarget(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The audio will be played only on the first channel. This is the default.
             *
             * Generated from Godot docs: AudioStreamPlayer.MIX_TARGET_STEREO
             */
            val STEREO: MixTarget get() = MixTarget(0L)
            /**
             * The audio will be played on all surround channels.
             *
             * Generated from Godot docs: AudioStreamPlayer.MIX_TARGET_SURROUND
             */
            val SURROUND: MixTarget get() = MixTarget(1L)
            /**
             * The audio will be played on the second channel, which is usually the center.
             *
             * Generated from Godot docs: AudioStreamPlayer.MIX_TARGET_CENTER
             */
            val CENTER: MixTarget get() = MixTarget(2L)
        }
    }
    // ===== END GENERATED ENUMS: AudioStreamPlayer =====

    fun setStreamFromPath(path: String) {
        ResourceLoader.loadAudioStream(path)?.use { stream ->
            setStream(stream)
        }
    }

    // AudioStreamPlayer.set_stream(stream) — null clears the assigned stream. Mirrors the
    // generated 2D/3D variants; routed through the existing cinterop glue (0 == null).
    fun setStream(stream: AudioStream?) {
        IosGodot.audioStreamPlayerSetStream(segment.address(), stream?.segment?.address() ?: 0L)
    }

    fun setPitchScale(value: Double) {
        IosGodot.audioStreamPlayerSetPitchScale(segment.address(), value)
    }

    fun setVolumeDb(value: Double) {
        IosGodot.audioStreamPlayerSetVolumeDb(segment.address(), value)
    }

    fun setBus(value: String) {
        IosGodot.audioStreamPlayerSetBus(segment.address(), value)
    }

    fun setStreamPaused(value: Boolean) {
        IosGodot.audioStreamPlayerSetStreamPaused(segment.address(), value)
    }

    fun play() {
        IosGodot.audioStreamPlayerPlay(segment.address(), 0.0)
    }

    fun stop() {
        ObjectCalls.ptrcallNoArgs(stopBind, segment)
    }

    /** Signal `finished()`; see [TypedSignal]. */
    val finished: Signal0
        @JvmName("finishedTypedSignal")
        get() = Signal0(this, "finished")

    companion object {
        private val stopBind by lazy { ObjectCalls.getMethodBind("AudioStreamPlayer", "stop", 3218959716L) }

        fun create(): AudioStreamPlayer =
            AudioStreamPlayer(GodotHandle(MemorySegment.ofAddress(IosGodot.constructObject("AudioStreamPlayer"))))
    }
}

// KANAMA-IOS-HANDWRITTEN: [platform] pure-Kotlin math helpers (no Godot call). Bespoke utility,
// matching the desktop Mathf facade (which is hand-authored, not generated).
object Mathf {
    const val PI: Double = kotlin.math.PI
    const val TAU: Double = kotlin.math.PI * 2.0

    fun abs(value: Double): Double = kotlin.math.abs(value)

    fun abs(value: Float): Float = kotlin.math.abs(value)

    fun min(a: Double, b: Double): Double = kotlin.math.min(a, b)

    fun max(a: Double, b: Double): Double = kotlin.math.max(a, b)

    fun min(a: Long, b: Long): Long = kotlin.math.min(a, b)

    fun max(a: Long, b: Long): Long = kotlin.math.max(a, b)

    fun cos(value: Double): Double = kotlin.math.cos(value)

    fun sin(value: Double): Double = kotlin.math.sin(value)

    fun sqrt(value: Double): Double = kotlin.math.sqrt(value)

    fun log(value: Double): Double = kotlin.math.ln(value)

    // Godot's @GlobalScope.inverse_lerp: the weight that lerp(from, to, w) == value.
    fun inverseLerp(from: Double, to: Double, value: Double): Double =
        if (to == from) 0.0 else (value - from) / (to - from)

    fun lerp(from: Double, to: Double, weight: Double): Double =
        from + (to - from) * weight

    // Godot's @GlobalScope.atan2 / pow — pure math, matching the desktop Mathf helpers.
    fun atan2(y: Double, x: Double): Double = kotlin.math.atan2(y, x)

    fun pow(x: Double, y: Double): Double = x.pow(y)

    // Godot's @GlobalScope.move_toward: step [from] toward [to] by at most [delta].
    fun moveToward(from: Double, to: Double, delta: Double): Double =
        if (kotlin.math.abs(to - from) <= delta) to
        else from + (if (to > from) 1.0 else -1.0) * delta

    // Godot's @GlobalScope.is_equal_approx (CMP_EPSILON fuzzy compare), via the shared iOS helper.
    fun isEqualApprox(a: Double, b: Double): Boolean =
        if (a == b) {
            true
        } else {
            val epsilon = 0.00001
            val tolerance = kotlin.math.max(epsilon * kotlin.math.abs(a), epsilon)
            kotlin.math.abs(a - b) < tolerance
        }

    fun lerpAngle(from: Double, to: Double, weight: Double): Double {
        val difference = ((to - from + PI) % (PI * 2.0)) - PI
        return from + difference * weight
    }

    fun clamp(value: Double, min: Double, max: Double): Double =
        value.coerceIn(min, max)

    // Godot's @GlobalScope.roundi (half away from zero, matching the desktop
    // Mathf.roundToInt -> GD.roundi path; kotlin.math.round ties-to-even would diverge on .5).
    fun roundToInt(value: Double): Long =
        if (value >= 0.0) kotlin.math.floor(value + 0.5).toLong()
        else kotlin.math.ceil(value - 0.5).toLong()
}

// KANAMA-IOS-HANDWRITTEN: [glue] ResourceLoader singleton. Not retired to the generated wrapper:
// the generated ResourceLoader.load() returns a bare Resource, but demos call the bespoke typed
// loaders (loadTexture2D/loadAudioStream/loadPackedScene), which pass a type_hint through the C
// shim and wrap the result to the concrete type. The generator emits no typed-load sugar, so this
// stays bespoke.
object ResourceLoader {
    // ===== BEGIN GENERATED ENUMS: ResourceLoader (scripts/generate_api_wrapper.py — do not edit) =====
    /**
     * Godot's `ResourceLoader.ThreadLoadStatus` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`ResourceLoader.ThreadLoadStatus.<NAME>`).
     *
     * Generated from Godot docs: ResourceLoader.ThreadLoadStatus
     */
    value class ThreadLoadStatus(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The resource is invalid, or has not been loaded with `load_threaded_request`.
             *
             * Generated from Godot docs: ResourceLoader.THREAD_LOAD_INVALID_RESOURCE
             */
            val INVALID_RESOURCE: ThreadLoadStatus get() = ThreadLoadStatus(0L)
            /**
             * The resource is still being loaded.
             *
             * Generated from Godot docs: ResourceLoader.THREAD_LOAD_IN_PROGRESS
             */
            val IN_PROGRESS: ThreadLoadStatus get() = ThreadLoadStatus(1L)
            /**
             * Some error occurred during loading and it failed.
             *
             * Generated from Godot docs: ResourceLoader.THREAD_LOAD_FAILED
             */
            val FAILED: ThreadLoadStatus get() = ThreadLoadStatus(2L)
            /**
             * The resource was loaded successfully and can be accessed via `load_threaded_get`.
             *
             * Generated from Godot docs: ResourceLoader.THREAD_LOAD_LOADED
             */
            val LOADED: ThreadLoadStatus get() = ThreadLoadStatus(3L)
        }
    }

    /**
     * Godot's `ResourceLoader.CacheMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`ResourceLoader.CacheMode.<NAME>`).
     *
     * Generated from Godot docs: ResourceLoader.CacheMode
     */
    value class CacheMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Neither the main resource (the one requested to be loaded) nor any of its subresources are
             * retrieved from cache nor stored into it. Dependencies (external resources) are loaded with
             * `CacheMode.REUSE`.
             *
             * Generated from Godot docs: ResourceLoader.CACHE_MODE_IGNORE
             */
            val IGNORE: CacheMode get() = CacheMode(0L)
            /**
             * The main resource (the one requested to be loaded), its subresources, and its dependencies
             * (external resources) are retrieved from cache if present, instead of loaded. Those not cached
             * are loaded and then stored into the cache. The same rules are propagated recursively down the
             * tree of dependencies (external resources).
             *
             * Generated from Godot docs: ResourceLoader.CACHE_MODE_REUSE
             */
            val REUSE: CacheMode get() = CacheMode(1L)
            /**
             * Like `CacheMode.REUSE`, but the cache is checked for the main resource (the one requested to be
             * loaded) as well as for each of its subresources. Those already in the cache, as long as the
             * loaded and cached types match, have their data refreshed from storage into the already existing
             * instances. Otherwise, they are recreated as completely new objects.
             *
             * Generated from Godot docs: ResourceLoader.CACHE_MODE_REPLACE
             */
            val REPLACE: CacheMode get() = CacheMode(2L)
            /**
             * Like `CacheMode.IGNORE`, but propagated recursively down the tree of dependencies (external
             * resources).
             *
             * Generated from Godot docs: ResourceLoader.CACHE_MODE_IGNORE_DEEP
             */
            val IGNORE_DEEP: CacheMode get() = CacheMode(3L)
            /**
             * Like `CacheMode.REPLACE`, but propagated recursively down the tree of dependencies (external
             * resources).
             *
             * Generated from Godot docs: ResourceLoader.CACHE_MODE_REPLACE_DEEP
             */
            val REPLACE_DEEP: CacheMode get() = CacheMode(4L)
        }
    }
    // ===== END GENERATED ENUMS: ResourceLoader =====

    fun load(path: String): Resource? =
        IosGodot.resourceLoaderLoad(path, "").takeIf { it != 0L }?.let {
            RefCounted.owned(Resource(GodotHandle(MemorySegment.ofAddress(it))))
        }

    fun loadTexture2D(path: String): Texture2D? =
        IosGodot.resourceLoaderLoad(path, "Texture2D").takeIf { it != 0L }?.let {
            RefCounted.owned(Texture2D(GodotHandle(MemorySegment.ofAddress(it))))
        }

    fun loadAudioStream(path: String): AudioStream? =
        IosGodot.resourceLoaderLoad(path, "AudioStream").takeIf { it != 0L }?.let {
            RefCounted.owned(AudioStream(GodotHandle(MemorySegment.ofAddress(it))))
        }

    fun loadPackedScene(path: String): PackedScene? =
        IosGodot.resourceLoaderLoad(path, "PackedScene").takeIf { it != 0L }?.let {
            RefCounted.owned(PackedScene(GodotHandle(MemorySegment.ofAddress(it))))
        }

    fun loadLightmapGIData(path: String): LightmapGIData? =
        IosGodot.resourceLoaderLoad(path, "LightmapGIData").takeIf { it != 0L }?.let {
            RefCounted.owned(LightmapGIData(GodotHandle(MemorySegment.ofAddress(it))))
        }

    /**
     * [loadThreadedGetStatusWithProgress]'s result (named `ThreadLoadStatus` before task 128 A, when
     * that name became Godot's enum `ResourceLoader.ThreadLoadStatus`).
     */
    data class ThreadLoadProgress(val status: ResourceLoader.ThreadLoadStatus, val progress: Double?)

    // Threaded loading: the request goes through the generic Variant call path (int return decodes
    // cleanly). The status poll ptrcalls load_threaded_get_status with the optional progress
    // out-Array supplied C-side, so progress is a real [0,1] value on iOS too.
    fun loadThreadedRequest(
        path: String,
        typeHint: String = "",
        useSubThreads: Boolean = false,
        cacheMode: ResourceLoader.CacheMode = ResourceLoader.CacheMode.REUSE,
    ): GodotError =
        GodotError((ObjectCalls.callWithVariantArgs(loadThreadedRequestBind, singleton, listOf(path, typeHint, useSubThreads, cacheMode.value)) as? Number)?.toLong()
            ?: 0L)

    fun loadThreadedGetStatusWithProgress(path: String): ThreadLoadProgress {
        val (status, progress) = ObjectCalls.ptrcallLoadStatusWithProgress(loadThreadedGetStatusBind, singleton, path)
        return if (status < 0) {
            ThreadLoadProgress(ResourceLoader.ThreadLoadStatus.INVALID_RESOURCE, null)
        } else {
            ThreadLoadProgress(ResourceLoader.ThreadLoadStatus(status), progress)
        }
    }

    // After a threaded load completes the resource is in the ResourceLoader cache, so fetch it
    // through the same synchronous C-shim as load() (which references the RefCounted resource
    // correctly). The generic Variant-call path returned a handle whose PackedScene.instantiate()
    // silently yielded null on device.
    fun loadThreadedGet(path: String): Resource? =
        IosGodot.resourceLoaderLoad(path, "").takeIf { it != 0L }?.let { RefCounted.owned(Resource(GodotHandle(MemorySegment.ofAddress(it)))) }

    fun loadThreadedGetPackedScene(path: String): PackedScene? =
        IosGodot.resourceLoaderLoad(path, "PackedScene").takeIf { it != 0L }?.let { RefCounted.owned(PackedScene(GodotHandle(MemorySegment.ofAddress(it)))) }

    private val singleton by lazy { ObjectCalls.getSingleton("ResourceLoader") }
    private val loadThreadedRequestBind by lazy { ObjectCalls.getMethodBind("ResourceLoader", "load_threaded_request", 3614384323L) }
    private val loadThreadedGetStatusBind by lazy { ObjectCalls.getMethodBind("ResourceLoader", "load_threaded_get_status", 4137685479L) }
    private val loadThreadedGetBind by lazy { ObjectCalls.getMethodBind("ResourceLoader", "load_threaded_get", 1748875256L) }
}

// KANAMA-IOS-HANDWRITTEN: [platform] GD global helpers (rand*, print) — Kotlin/native impls, bespoke.
object GD {
    fun randomize() {
    }

    fun randi(): Long =
        Random.nextLong().let { if (it == Long.MIN_VALUE) 0L else kotlin.math.abs(it) }

    fun randf(): Double =
        Random.nextDouble()

    fun randiRange(from: Long, to: Long): Long =
        if (to <= from) from else Random.nextLong(from, to + 1)

    fun randfRange(from: Double, to: Double): Double =
        if (to <= from) from else Random.nextDouble(from, to)

    // Godot's @GlobalScope.randfn: normally-distributed pseudo-random via Box-Muller.
    fun randfn(mean: Double, deviation: Double): Double {
        val u1 = Random.nextDouble().coerceAtLeast(Double.MIN_VALUE)
        val u2 = Random.nextDouble()
        val standard = kotlin.math.sqrt(-2.0 * kotlin.math.ln(u1)) * kotlin.math.cos(2.0 * Mathf.PI * u2)
        return mean + deviation * standard
    }

    // @GlobalScope print facade. iOS has no utility-function call path (see the isInstanceValid note
    // + roadmap), so these route to the Kotlin/Native console (device log / xcrun devicectl --console)
    // instead of Godot's print stream. Output is APPROXIMATE — right text, different sink — matching
    // the demos' debug-logging use. Godot's print() concatenates its args with no separator.
    private fun joinValues(values: Array<out Any?>): String =
        values.joinToString("") { it?.toString() ?: "<null>" }

    fun print(vararg values: Any?): Unit = println(joinValues(values))

    fun printRich(vararg values: Any?): Unit = println(joinValues(values))

    fun printErr(vararg values: Any?): Unit = println(joinValues(values))

    fun printS(vararg values: Any?): Unit = println(values.joinToString(" ") { it?.toString() ?: "<null>" })

    fun printRaw(vararg values: Any?): Unit = kotlin.io.print(joinValues(values))

    fun printVerbose(vararg values: Any?): Unit = println(joinValues(values))

    fun pushWarning(vararg values: Any?): Unit = println("WARNING: " + joinValues(values))

    fun pushError(vararg values: Any?): Unit = println("ERROR: " + joinValues(values))

    // @GlobalScope math facade (pure-Kotlin, matching the desktop GD utility helpers).
    fun signf(value: Double): Double = kotlin.math.sign(value)

    fun lerpf(from: Double, to: Double, weight: Double): Double =
        from + (to - from) * weight

    fun clampf(value: Double, min: Double, max: Double): Double =
        value.coerceIn(min, max)

    fun lerpAngle(from: Double, to: Double, weight: Double): Double =
        Mathf.lerpAngle(from, to, weight)

    fun remap(value: Double, istart: Double, istop: Double, ostart: Double, ostop: Double): Double =
        ostart + (ostop - ostart) * ((value - istart) / (istop - istart))

    fun degToRad(degrees: Double): Double = degrees * (Mathf.PI / 180.0)

    fun radToDeg(radians: Double): Double = radians * (180.0 / Mathf.PI)

    // @GlobalScope.is_instance_valid (task 98 mirror). Answered by the ObjectDB lookup of the
    // instance id the wrapper captured at construction (shim object_get_instance_from_id), so a
    // wrapper whose object was freed reports false without dereferencing the dead pointer —
    // the same contract as desktop GD.isInstanceValid. Non-object values are simply non-null.
    fun isInstanceValid(value: Any?): Boolean =
        when (value) {
            null -> false
            is GodotObject -> IosGodot.isInstanceIdValid(value.instanceId)
            else -> true
        }

    // @GlobalScope.is_instance_id_valid: the id-shaped counterpart of isInstanceValid.
    fun isInstanceIdValid(id: Long): Boolean = IosGodot.isInstanceIdValid(id)
}

inline fun <reified T> GodotObject.kotlinScriptInstance(): T? =
    net.multigesture.kanama.ios.iosScriptInstanceForOwner(handle.segment.address()) as? T

inline fun <reified T> Node.kotlinScriptInstance(): T? =
    GodotObject(handle).kotlinScriptInstance<T>()

@OptIn(ExperimentalForeignApi::class)
// KANAMA-IOS-HANDWRITTEN: [seam] thin cinterop facade over the C shim entry points the runtime and
// the bespoke classes above still call (instance ids, object construction, typed loads, signal
// connections, AudioStreamPlayer). Task 129 A removed the 53 functions nothing called any more.
internal object IosGodot {
    fun objectGetInstanceId(objectHandle: Long): Long =
        kanama_ios_godot_object_get_instance_id(objectHandle)

    fun isInstanceIdValid(instanceId: Long): Boolean =
        kanama_ios_godot_is_instance_id_valid(instanceId) != 0

    fun objectIsLive(objectHandle: Long, instanceId: Long): Boolean =
        net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_is_live(objectHandle, instanceId) != 0

    fun resourceLoaderLoad(path: String, typeHint: String): Long =
        kanama_ios_godot_resource_loader_load(path, typeHint)

    fun constructObject(className: String): Long =
        kanama_ios_godot_construct_object(className)

    fun audioStreamPlayerSetStream(player: Long, stream: Long) {
        kanama_ios_godot_audio_stream_player_set_stream(player, stream)
    }

    fun audioStreamPlayerSetVolumeDb(player: Long, volumeDb: Double) {
        kanama_ios_godot_audio_stream_player_set_volume_db(player, volumeDb)
    }

    fun audioStreamPlayerSetPitchScale(player: Long, pitchScale: Double) {
        kanama_ios_godot_audio_stream_player_set_pitch_scale(player, pitchScale)
    }

    fun audioStreamPlayerSetBus(player: Long, bus: String) {
        kanama_ios_godot_audio_stream_player_set_bus(player, bus)
    }

    fun audioStreamPlayerSetStreamPaused(player: Long, paused: Boolean) {
        kanama_ios_godot_audio_stream_player_set_stream_paused(player, if (paused) 1 else 0)
    }

    fun audioStreamPlayerPlay(player: Long, fromPosition: Double) {
        kanama_ios_godot_audio_stream_player_play(player, fromPosition)
    }

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

@file:Suppress("unused")

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.* // generated ObjectCalls.* extension helpers
import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.CName
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_canvas_item_hide
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_canvas_item_get_local_mouse_position
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_canvas_item_get_viewport_rect
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_canvas_item_set_modulate
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_canvas_item_show
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_collision_shape3d_set_disabled
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_get_method_bind
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_gpu_particles2d_restart
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_gpu_particles2d_set_emitting
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_gpu_particles2d_set_lifetime
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_gpu_particles3d_restart
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_gpu_particles3d_set_emitting
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_input_event_is_pressed
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_input_event_is_released
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_input_event_mouse_button_get_button_index
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_create_tween
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node2d_get_position
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node2d_get_scale
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node2d_set_position
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node2d_set_scale
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_get_global_position
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_get_position
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_get_rotation
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_get_scale
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_rotate_y
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_set_global_position
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_set_position
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_set_rotation
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node3d_set_scale
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_add_child
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_get_child
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_get_child_count
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_get_node_or_null
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_get_tree
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_get_viewport
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_is_in_group
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_remove_child
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_set_process_input
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_play
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_set_bus
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_set_pitch_scale
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_set_stream
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_set_stream_paused
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_audio_stream_player_set_volume_db
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_construct_object
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_set_process_unhandled_input
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_connect
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_connect_callable
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_disconnect_callable
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_disconnect
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_emit_signal_int
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_emit_signal_vector2i
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_is_class
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_queue_free
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_packed_scene_instantiate
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_resource_loader_load
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_sprite2d_set_texture
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_tween_kill
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_tween_set_parallel
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_tween_tween_property_color
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_tween_tween_property_vector2
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_tween_tween_callback
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_tween_tween_method
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_property_tweener_from_color
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_tweener_set_ease
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_tweener_set_trans
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_viewport_get_visible_rect
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.math.PI
import kotlin.random.Random

@RequiresOptIn(
    message = "This API exposes a Godot object whose lifetime is owned outside Kotlin.",
    level = RequiresOptIn.Level.WARNING,
)
annotation class ManualGodotLifetimeApi

abstract class KanamaScript<Self : Any>(
    val godotObject: MemorySegment,
    selfFactory: (MemorySegment) -> Self,
) {
    val self: Self = selfFactory(godotObject)

    inline fun <T> selfAs(ctor: (MemorySegment) -> T): T = ctor(godotObject)
}

// KANAMA-IOS-HANDWRITTEN: [platform] KanamaScope bridges Godot's main thread to Kotlin coroutines; not generatable from extension_api.json.
class KanamaScope : CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    fun cancel() {
        job.cancel()
    }
}

interface KanamaCoroutineOwner {
    val kanamaScope: KanamaScope
}

// KANAMA-IOS-HANDWRITTEN: [platform] MainThread.post is a no-op shim; on iOS main thread dispatch is handled by Godot's frame loop, not a JVM executor.
object MainThread {
    // Continuations parked by [awaitNextFrame], resumed once per engine frame by
    // KanamaIosRuntime.frame() via [pumpNextFrame]. Accessed only on the engine main thread
    // (awaitNextFrame runs under Dispatchers.Main; frame() runs on the engine main thread).
    private val nextFrameContinuations = mutableListOf<CancellableContinuation<Unit>>()
    private val nextFrameTasks = mutableListOf<() -> Unit>()

    fun post(action: () -> Unit) {
        action()
    }

    // Run [action] after at least one engine frame pump (mirrors desktop MainThread.postNextFrame).
    fun postNextFrame(action: () -> Unit) {
        nextFrameTasks.add(action)
    }

    // Run [action] after at least [frames] engine frame pumps (mirrors desktop postAfterFrames).
    fun postAfterFrames(frames: Int, action: () -> Unit) {
        if (frames <= 0) {
            action()
            return
        }
        postNextFrame { postAfterFrames(frames - 1, action) }
    }

    // Suspend until the next engine frame is pumped (mirrors desktop MainThread.awaitNextFrame).
    // Frame-based waiting is robust to device frame rate, unlike a wall-clock delay.
    suspend fun awaitNextFrame() {
        suspendCancellableCoroutine { continuation ->
            nextFrameContinuations.add(continuation)
            continuation.invokeOnCancellation { nextFrameContinuations.remove(continuation) }
        }
    }

    // Once per engine frame: run parked tasks, then resume parked continuations. Both are
    // snapshot-then-cleared so work re-queued by a resumed coroutine/task waits for the next frame
    // (matching desktop's one-step-per-frame semantics).
    internal fun pumpNextFrame() {
        if (nextFrameTasks.isNotEmpty()) {
            val tasks = nextFrameTasks.toList()
            nextFrameTasks.clear()
            for (task in tasks) task()
        }
        if (nextFrameContinuations.isEmpty()) return
        val pending = nextFrameContinuations.toList()
        nextFrameContinuations.clear()
        for (continuation in pending) {
            if (continuation.isActive) continuation.resume(Unit)
        }
    }
}

open class GodotObject(
    val handle: MemorySegment,
) : AutoCloseable {
    constructor(handle: Long) : this(MemorySegment.ofAddress(handle))

    fun requireOpenHandle(): MemorySegment = handle

    fun isClass(className: String): Boolean =
        className.isNotBlank() && IosGodot.objectIsClass(handle.address(), className)

    // KANAMA-IOS-HANDWRITTEN: [runtime] signal/connect/emitSignal/await use the custom GDExtension
    // Callable + IosCallableRegistry (lambda/bound dispatch); bespoke runtime, not generated.
    fun signal(name: String): GodotSignal =
        GodotSignal(this, name)

    // Variant Object.call dispatch: [method, *args] boxed into Variants, called via the
    // Variant path (the varargs path ptrcall can't express). Scalar return decoded to Any?.
    fun call(method: String, vararg args: Any?): Any? =
        ObjectCalls.callWithVariantArgs(callBind, handle, listOf(method, *args))

    // Object.set_deferred(property, value) via the Variant path; applies on the next idle frame.
    fun setDeferred(property: String, value: Any?) {
        ObjectCalls.callWithVariantArgs(setDeferredBind, handle, listOf(property, value))
    }

    // Object.set_script(resource) via the Variant call path. Signature matches desktop
    // GodotObject.setScript(Resource?). NOTE: desktop also calls ScriptBridge.noteSetScript to
    // arm pre-instance script-property buffering; iOS does not mirror that buffering (see set()).
    fun setScript(script: Resource?) {
        call("set_script", script)
    }

    // Object.set(property, value) via the Variant call path. Signature/return match desktop
    // GodotObject.set (returns 0L). NOTE: desktop also calls ScriptBridge.applyOrRecordScriptPropertySet,
    // which buffers a value set on a Kanama-script owner *before* its Kotlin instance exists and
    // replays it once the instance is created. iOS receives engine-driven property sets directly
    // through KanamaIosScriptBridge, so the steady-state path needs no buffering; the rare
    // "set a script property before the instance is ready" case is not buffered on iOS.
    fun set(property: String, value: Any?): Long {
        call("set", property, value)
        return 0L
    }

    // Object.get(property) via the Variant call path. Return type matches desktop GodotObject.get(): Any?.
    fun get(property: String): Any? = call("get", property)

    // Object.has_method(name) — ptrcall (StringName arg, bool ret), mirroring desktop GodotObject.
    fun hasMethod(method: String): Boolean =
        ObjectCalls.ptrcallWithStringNameArgRetBool(hasMethodBind, handle, method)

    // Object.get_instance_id() via the Variant call path (int64 return). Matches desktop GodotObject.
    fun getInstanceId(): Long =
        (call("get_instance_id") as? Long) ?: 0L

    // Object.is_queued_for_deletion() — ptrcall (no args, bool ret), mirroring desktop GodotObject.
    fun isQueuedForDeletion(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isQueuedForDeletionBind, handle)

    fun connect(signalName: String, target: GodotObject, method: String, flags: Long = CONNECT_DEFAULT): Long =
        IosGodot.objectConnect(handle.address(), signalName, target.handle.address(), method, flags)

    // Object.disconnect(signal, Callable(target, method)) — symmetric to connect().
    fun disconnect(signalName: String, target: GodotObject, method: String) {
        IosGodot.objectDisconnect(handle.address(), signalName, target.handle.address(), method)
    }

    // Object.connect(signal, Callable(target, method).bindv([boundArgs]), flags). Routes through the
    // C shim's bound-Callable path (Array of PT-tagged bound args -> Callable.bindv). Phase 4.1.
    fun connectBound(
        signalName: String,
        target: GodotObject,
        method: String,
        boundArgs: List<Any?>,
        flags: Long = CONNECT_DEFAULT,
    ): Long = ObjectCalls.connectBound(handle, signalName, target.handle, method, boundArgs, flags)

    // Symmetric teardown — rebuilds the same bound Callable so Object.disconnect matches. Phase 4.1.
    fun disconnectBound(signalName: String, target: GodotObject, method: String, boundArgs: List<Any?>) {
        ObjectCalls.disconnectBound(handle, signalName, target.handle, method, boundArgs)
    }

    fun emitSignal(signalName: String, value: Int): Int =
        IosGodot.objectEmitSignalInt(handle.address(), signalName, value.toLong())

    fun emitSignal(signalName: String, value: Long): Int =
        IosGodot.objectEmitSignalInt(handle.address(), signalName, value)

    fun emitSignal(signalName: String, value: Vector2i): Int =
        IosGodot.objectEmitSignalVector2i(handle.address(), signalName, value.x.toLong(), value.y.toLong())

    fun emitSignal(signalName: String, vararg args: Any?) {
        when {
            // Keep the bespoke C-shim fast paths for the single scalar args they cover...
            args.size == 1 && args[0] is Int -> emitSignal(signalName, args[0] as Int)
            args.size == 1 && args[0] is Long -> emitSignal(signalName, args[0] as Long)
            args.size == 1 && args[0] is Vector2i -> emitSignal(signalName, args[0] as Vector2i)
            // ...and route everything else (no-arg signals, and any other arg shapes) through
            // Object.emit_signal via the Variant call path. The previous `when` silently dropped
            // no-arg signals (empty args matched nothing), so e.g. a no-arg @Signal never fired.
            else -> call("emit_signal", signalName, *args)
        }
    }

    // KANAMA-IOS-HANDWRITTEN: [platform] AutoCloseable no-op base; Godot object lifetime is managed
    // externally (the Kotlin wrapper does not own a ref), not by Kotlin's close(). Implementing
    // AutoCloseable lets shared demo code use `obj.use { ... }` (e.g. getSlideCollision) uniformly.
    override fun close() {
    }

    companion object {
        const val CONNECT_DEFAULT = 0L
        const val CONNECT_ONE_SHOT = 4L

        // Variant-call binds (resolved once) for the dynamic Object.call / set_deferred path.
        private val callBind by lazy { ObjectCalls.getMethodBind("Object", "call", 3400424181L) }
        private val setDeferredBind by lazy { ObjectCalls.getMethodBind("Object", "set_deferred", 3776071444L) }
        private val hasMethodBind by lazy { ObjectCalls.getMethodBind("Object", "has_method", 2619796661L) }
        private val isQueuedForDeletionBind by lazy { ObjectCalls.getMethodBind("Object", "is_queued_for_deletion", 36873697L) }

        fun fromHandle(handle: MemorySegment): GodotObject? = wrap(handle)

        internal fun wrap(handle: MemorySegment): GodotObject? =
            if (handle.address() == 0L) null else GodotObject(handle)
    }
}

class GodotSignal internal constructor(
    private val owner: GodotObject,
    val name: String,
) {
    fun connect(target: GodotObject, method: String, flags: Long = GodotObject.CONNECT_DEFAULT): Long =
        owner.connect(name, target, method, flags)

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
        val result = IosGodot.objectConnectCallable(owner.handle.address(), name, callbackId, flags)
        if (result != 0L) {
            // connect failed; Godot freed the callable (which released the entry),
            // but release defensively in case it never reached the trampoline path.
            IosCallableRegistry.release(callbackId)
        }
        return SignalConnection(result, owner, name, callbackId)
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
}

class SignalConnection internal constructor(
    // Real Object.connect return Error (0 == OK) from the lambda-connect path.
    val error: Long = 0L,
    private val owner: GodotObject? = null,
    private val signalName: String = "",
    private val callbackId: Long = 0L,
) : AutoCloseable {
    private var closed = false

    // Disconnect the lambda Callable. The C path recreates the identity-equal custom Callable
    // (call_func + callback_id) and Object.disconnects it; the connection's free_func then releases
    // the registry entry. No-op if the connect failed or close() was already called. (A
    // CONNECT_ONE_SHOT connection auto-disconnects when it fires; calling close() afterwards is a
    // benign redundant disconnect.) Phase 4.1b.
    override fun close() {
        if (closed || error != 0L || owner == null || callbackId == 0L) {
            return
        }
        closed = true
        IosGodot.objectDisconnectCallable(owner.handle.address(), signalName, callbackId)
    }
}

open class StaticBody3D(handle: MemorySegment) : Node3D(handle)

class SceneTree(handle: MemorySegment) : Node(handle) {
    fun quit(exitCode: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(quitBind, handle, exitCode)
    }

    fun reloadCurrentScene(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(reloadCurrentSceneBind, handle)

    // SceneTree.call_group(group, method, ...args) via the Variant call path (it is a varargs
    // method, which the audited ptrcall set can't express — matches desktop SceneTree.callGroup).
    fun callGroup(group: String, method: String, vararg args: Any?) {
        call("call_group", group, method, *args)
    }

    // Instance form so demo code can write `getTree().delaySeconds(...)` (Android/desktop model
    // SceneTree as a singleton object where the same call resolves; the companion form below also
    // works). Pure coroutine delay, no engine call.
    suspend fun delaySeconds(seconds: Double) {
        delay((seconds * 1000.0).toLong().coerceAtLeast(0L))
    }

    fun setPaused(paused: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPausedBind, handle, paused)
    }

    fun unloadCurrentScene() {
        ObjectCalls.ptrcallNoArgs(unloadCurrentSceneBind, handle)
    }

    fun isPaused(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isPausedBind, handle)

    override fun createTween(): Tween? =
        ObjectCalls.ptrcallNoArgsRetObject(createTweenBind, handle)
            .takeIf { it.address() != 0L }
            ?.let { Tween(it) }

    // SceneTree.get_nodes_in_group(group) -> Array[Node]. The (StringName)->typed-object-array ptrcall
    // shape isn't wired, so this goes through the Variant call path; OBJECT elements surface as raw
    // handles (or wrapped objects), wrapped back to Node. A non-array/unsupported decode yields empty
    // (only the F10 free-camera HUD toggle uses this — graceful no-op if the decode degrades).
    fun getNodesInGroup(group: String): List<Node> =
        (call("get_nodes_in_group", group) as? List<*>)?.mapNotNull { element ->
            when (element) {
                is Node -> element
                is GodotObject -> Node(element.handle)
                is MemorySegment -> Node(element)
                is Long -> Node(MemorySegment.ofAddress(element))
                else -> null
            }
        } ?: emptyList()

    // The root Window handle (an Object); wrap with Window(...) or Node(...) at the call site,
    // matching desktop SceneTree.getRoot(): MemorySegment.
    fun getRoot(): MemorySegment =
        ObjectCalls.ptrcallNoArgsRetObject(getRootBind, handle)

    companion object {
        private val quitBind by lazy { ObjectCalls.getMethodBind("SceneTree", "quit", 1995695955L) }
        private val reloadCurrentSceneBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "reload_current_scene", 166280745L)
        }
        private val setPausedBind by lazy { ObjectCalls.getMethodBind("SceneTree", "set_pause", 2586408642L) }
        private val unloadCurrentSceneBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "unload_current_scene", 3218959716L)
        }
        private val isPausedBind by lazy { ObjectCalls.getMethodBind("SceneTree", "is_paused", 36873697L) }
        private val createTweenBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "create_tween", 3426978995L)
        }
        private val getRootBind by lazy { ObjectCalls.getMethodBind("SceneTree", "get_root", 1757182445L) }

        suspend fun delaySeconds(seconds: Double) {
            delay((seconds * 1000.0).toLong().coerceAtLeast(0L))
        }

        // Static-call forms: demos written against desktop/Android (where SceneTree is reachable
        // statically) call `SceneTree.quit()` / `SceneTree.unloadCurrentScene()`. Resolve the active
        // tree via Engine.get_main_loop() and delegate to the instance method.
        private fun active(): SceneTree = SceneTree(Engine.getMainLoop())

        fun quit(exitCode: Int = 0) = active().quit(exitCode)

        fun unloadCurrentScene() = active().unloadCurrentScene()
    }
}

class AudioStreamPlayer(handle: MemorySegment) : Node(handle) {
    fun setStreamFromPath(path: String) {
        val stream = IosGodot.resourceLoaderLoad(path, "")
        if (stream != 0L) {
            IosGodot.audioStreamPlayerSetStream(handle.address(), stream)
        }
    }

    // AudioStreamPlayer.set_stream(stream) — null clears the assigned stream. Mirrors the
    // generated 2D/3D variants; routed through the existing cinterop glue (0 == null).
    fun setStream(stream: AudioStream?) {
        IosGodot.audioStreamPlayerSetStream(handle.address(), stream?.handle?.address() ?: 0L)
    }

    fun setPitchScale(value: Double) {
        IosGodot.audioStreamPlayerSetPitchScale(handle.address(), value)
    }

    fun setVolumeDb(value: Double) {
        IosGodot.audioStreamPlayerSetVolumeDb(handle.address(), value)
    }

    fun setBus(value: String) {
        IosGodot.audioStreamPlayerSetBus(handle.address(), value)
    }

    fun setStreamPaused(value: Boolean) {
        IosGodot.audioStreamPlayerSetStreamPaused(handle.address(), value)
    }

    fun play() {
        IosGodot.audioStreamPlayerPlay(handle.address(), 0.0)
    }

    fun stop() {
        ObjectCalls.ptrcallNoArgs(stopBind, handle)
    }

    companion object {
        private val stopBind by lazy { ObjectCalls.getMethodBind("AudioStreamPlayer", "stop", 3218959716L) }

        fun create(): AudioStreamPlayer =
            AudioStreamPlayer(MemorySegment.ofAddress(IosGodot.constructObject("AudioStreamPlayer")))
    }
}

// KANAMA-IOS-HANDWRITTEN: [runtime] Tweener/PropertyTweener/Tween use the Variant tween_property path
// (final-value is a Variant), not generatable via the audited ptrcall set. Bespoke by design.
open class Tweener(handle: MemorySegment) : GodotObject(handle) {
    fun setTrans(value: Long): Tweener {
        IosGodot.tweenerSetTrans(handle.address(), value)
        return this
    }

    fun setEase(value: Long): Tweener {
        IosGodot.tweenerSetEase(handle.address(), value)
        return this
    }
}

class PropertyTweener(handle: MemorySegment) : Tweener(handle) {
    // PropertyTweener.from(value) — sets the tween's starting value. The demos only use a Color
    // (modulate) start; routed through the C shim (Variant arg). Returns this for chaining.
    fun from(value: Color): PropertyTweener {
        IosGodot.propertyTweenerFromColor(
            handle.address(),
            value.r.toDouble(), value.g.toDouble(), value.b.toDouble(), value.a.toDouble(),
        )
        return this
    }
}

class CallbackTweener(handle: MemorySegment) : Tweener(handle)

class Tween(handle: MemorySegment) : GodotObject(handle) {
    fun setParallel(parallel: Boolean): Tween {
        IosGodot.tweenSetParallel(handle.address(), if (parallel) 1 else 0)
        return this
    }

    // Tween.bind_node(node) — ptrcall (object arg, returns self). Mirrors desktop Tween.bindNode.
    fun bindNode(node: Node): Tween {
        ObjectCalls.ptrcallWithObjectArgRetObject(bindNodeBind, handle, node.handle)
        return this
    }

    // Tween.set_ease(ease) — ptrcall (int arg, returns self). Tween-level default ease (distinct
    // from Tweener.setEase, which configures an individual tweener).
    fun setEase(ease: Long): Tween {
        ObjectCalls.ptrcallWithLongArgRetObject(setEaseBind, handle, ease)
        return this
    }

    // Tween.tween_callback(Callable(target, method)). Routed through the C shim (Callable arg).
    fun tweenCallback(target: GodotObject, method: String): CallbackTweener? =
        IosGodot.tweenTweenCallback(handle.address(), target.handle.address(), method)
            .takeIf { it != 0L }
            ?.let { CallbackTweener(MemorySegment.ofAddress(it)) }

    // Tween.tween_method(Callable(target, method), from, to, duration) — animates [from]->[to] over
    // [duration], calling target.method(value) each frame. Callable arg → routed through the C shim.
    fun tweenMethod(target: GodotObject, method: String, from: Double, to: Double, duration: Double): Tweener? =
        IosGodot.tweenTweenMethod(handle.address(), target.handle.address(), method, from, to, duration)
            .takeIf { it != 0L }
            ?.let { Tweener(MemorySegment.ofAddress(it)) }

    fun tweenProperty(target: GodotObject, property: String, finalValue: Any?, duration: Double): PropertyTweener? {
        val addr = handle.address()
        val targetAddr = target.handle.address()
        return when (finalValue) {
            is Vector2 -> IosGodot.tweenTweenPropertyVector2(addr, targetAddr, property, finalValue.x.toDouble(), finalValue.y.toDouble(), duration)
            is Color -> IosGodot.tweenTweenPropertyColor(addr, targetAddr, property, finalValue.r.toDouble(), finalValue.g.toDouble(), finalValue.b.toDouble(), finalValue.a.toDouble(), duration)
            else -> IosGodot.tweenTweenPropertyVector2(addr, targetAddr, property, 0.0, 0.0, duration)
        }.takeIf { it != 0L }?.let { PropertyTweener(MemorySegment.ofAddress(it)) }
    }

    fun kill() {
        IosGodot.tweenKill(handle.address())
    }

    object Signals {
        const val finished: String = "finished"
    }

    companion object {
        const val TRANS_BACK = 10L
        const val TRANS_ELASTIC = 6L
        const val EASE_IN = 0L
        const val EASE_OUT = 1L
        const val EASE_IN_OUT = 2L
        const val EASE_OUT_IN = 3L

        private val bindNodeBind by lazy { ObjectCalls.getMethodBind("Tween", "bind_node", 2946786331L) }
        private val setEaseBind by lazy { ObjectCalls.getMethodBind("Tween", "set_ease", 1208117252L) }
    }
}

class InputEventMouseButton(handle: MemorySegment) : GodotObject(handle) {
    fun getButtonIndex(): Long =
        if (isClass("InputEventMouseButton")) IosGodot.inputEventMouseButtonGetButtonIndex(handle.address())
        else MOUSE_BUTTON_LEFT

    fun isPressed(): Boolean =
        IosGodot.inputEventIsPressed(handle.address())

    fun isReleased(): Boolean =
        IosGodot.inputEventIsReleased(handle.address())

    companion object {
        const val MOUSE_BUTTON_LEFT = 1L
        const val MOUSE_BUTTON_RIGHT = 2L
        const val MOUSE_BUTTON_MIDDLE = 3L

        fun from(value: GodotObject): InputEventMouseButton? =
            if (value.isClass("InputEventMouseButton")) InputEventMouseButton(value.handle)
            else if (value.isClass("InputEventScreenTouch")) InputEventMouseButton(value.handle)
            else null
    }
}

// Input is now a generated iOS wrapper (api/Input.kt, `object Input`), matching desktop's
// generated Input. The previous hand-written stub (getAxis/isActionJustPressed/setCustomMouseCursor)
// is retired; the generated object is a superset. setCustomMouseCursor remains guardrail-skipped on
// iOS (Variant Object arg) and was unused.

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
}

// KANAMA-IOS-HANDWRITTEN: [glue] ResourceLoader singleton. Not retired to the generated wrapper:
// the generated ResourceLoader.load() returns a bare Resource, but demos call the bespoke typed
// loaders (loadTexture2D/loadAudioStream/loadPackedScene), which pass a type_hint through the C
// shim and wrap the result to the concrete type. The generator emits no typed-load sugar, so this
// stays bespoke.
object ResourceLoader {
    fun load(path: String): Resource? =
        IosGodot.resourceLoaderLoad(path, "").takeIf { it != 0L }?.let {
            Resource(MemorySegment.ofAddress(it))
        }

    fun loadTexture2D(path: String): Texture2D? =
        IosGodot.resourceLoaderLoad(path, "Texture2D").takeIf { it != 0L }?.let {
            Texture2D(MemorySegment.ofAddress(it))
        }

    fun loadAudioStream(path: String): AudioStream? =
        IosGodot.resourceLoaderLoad(path, "AudioStream").takeIf { it != 0L }?.let {
            AudioStream(MemorySegment.ofAddress(it))
        }

    fun loadPackedScene(path: String): PackedScene? =
        IosGodot.resourceLoaderLoad(path, "PackedScene").takeIf { it != 0L }?.let {
            PackedScene(MemorySegment.ofAddress(it))
        }
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

    // @GlobalScope.is_instance_valid. Desktop routes this through the engine is_instance_valid
    // UtilityFunction; iOS has no utility-function call path (see roadmap), so this is an
    // APPROXIMATION: non-null, and for an engine object a live (non-zero) handle. It does not detect
    // an object freed while a non-null wrapper is still held — acceptable for the current demos.
    fun isInstanceValid(value: Any?): Boolean =
        when (value) {
            null -> false
            is GodotObject -> value.handle.address() != 0L
            else -> true
        }
}

inline fun <reified T> GodotObject.kotlinScriptInstance(): T? =
    net.multigesture.kanama.ios.iosScriptInstanceForOwner(handle.address()) as? T

inline fun <reified T> Node.kotlinScriptInstance(): T? =
    GodotObject(handle).kotlinScriptInstance<T>()

@OptIn(ExperimentalForeignApi::class)
// KANAMA-IOS-HANDWRITTEN: [glue] thin cinterop facade over the C shim helpers used by the bespoke
// classes above. Predates the generated ObjectCalls path; kept for the bespoke runtime.
internal object IosGodot {
    private const val LABEL_SET_TEXT_HASH = 83702148L
    private var labelSetTextBind = 0L

    fun setObjectText(objectHandle: Long, value: String): Boolean {
        val bind = labelSetTextBind()
        if (bind == 0L || objectHandle == 0L) {
            return false
        }
        net.multigesture.kanama.ios.cinterop.kanama_ios_godot_ptrcall_string_arg(bind, objectHandle, value)
        return true
    }

    fun objectQueueFree(objectHandle: Long) {
        kanama_ios_godot_object_queue_free(objectHandle)
    }

    fun objectIsClass(objectHandle: Long, className: String): Boolean =
        kanama_ios_godot_object_is_class(objectHandle, className) != 0

    fun nodeIsInGroup(node: Long, groupName: String): Boolean =
        kanama_ios_godot_node_is_in_group(node, groupName) != 0

    fun inputEventIsPressed(event: Long): Boolean =
        kanama_ios_godot_input_event_is_pressed(event) != 0

    fun inputEventIsReleased(event: Long): Boolean =
        kanama_ios_godot_input_event_is_released(event) != 0

    fun inputEventMouseButtonGetButtonIndex(event: Long): Long =
        kanama_ios_godot_input_event_mouse_button_get_button_index(event)

    fun nodeAddChild(parent: Long, child: Long) {
        kanama_ios_godot_node_add_child(parent, child)
    }

    fun nodeRemoveChild(parent: Long, child: Long) {
        kanama_ios_godot_node_remove_child(parent, child)
    }

    fun nodeGetChildCount(node: Long): Long =
        kanama_ios_godot_node_get_child_count(node)

    fun nodeGetChild(node: Long, index: Int): Long =
        kanama_ios_godot_node_get_child(node, index)

    fun nodeGetNodeOrNull(node: Long, path: String): Long =
        kanama_ios_godot_node_get_node_or_null(node, path)

    fun nodeGetTree(node: Long): Long =
        kanama_ios_godot_node_get_tree(node)

    fun nodeGetViewport(node: Long): Long =
        kanama_ios_godot_node_get_viewport(node)

    fun nodeCreateTween(node: Long): Long =
        kanama_ios_godot_node_create_tween(node)

    fun nodeSetProcessInput(node: Long, enabled: Boolean) {
        kanama_ios_godot_node_set_process_input(node, if (enabled) 1 else 0)
    }

    fun nodeSetProcessUnhandledInput(node: Long, enabled: Boolean) {
        kanama_ios_godot_node_set_process_unhandled_input(node, if (enabled) 1 else 0)
    }

    fun node2dGetPosition(node: Long): Vector2 =
        memScoped {
            val x = alloc<DoubleVarCompat>()
            val y = alloc<DoubleVarCompat>()
            kanama_ios_godot_node2d_get_position(node, x.ptr, y.ptr)
            Vector2(x.value, y.value)
        }

    fun node2dSetPosition(node: Long, value: Vector2) {
        kanama_ios_godot_node2d_set_position(node, value.x.toDouble(), value.y.toDouble())
    }

    fun node2dGetScale(node: Long): Vector2 =
        memScoped {
            val x = alloc<DoubleVarCompat>()
            val y = alloc<DoubleVarCompat>()
            kanama_ios_godot_node2d_get_scale(node, x.ptr, y.ptr)
            Vector2(x.value, y.value)
        }

    fun node2dSetScale(node: Long, value: Vector2) {
        kanama_ios_godot_node2d_set_scale(node, value.x.toDouble(), value.y.toDouble())
    }

    fun node3dGetPosition(node: Long): Vector3 =
        node3dGetVector3(node, ::kanama_ios_godot_node3d_get_position)

    fun node3dSetPosition(node: Long, value: Vector3) {
        kanama_ios_godot_node3d_set_position(node, value.x.toDouble(), value.y.toDouble(), value.z.toDouble())
    }

    fun node3dGetRotation(node: Long): Vector3 =
        node3dGetVector3(node, ::kanama_ios_godot_node3d_get_rotation)

    fun node3dSetRotation(node: Long, value: Vector3) {
        kanama_ios_godot_node3d_set_rotation(node, value.x.toDouble(), value.y.toDouble(), value.z.toDouble())
    }

    fun node3dGetScale(node: Long): Vector3 =
        node3dGetVector3(node, ::kanama_ios_godot_node3d_get_scale)

    fun node3dSetScale(node: Long, value: Vector3) {
        kanama_ios_godot_node3d_set_scale(node, value.x.toDouble(), value.y.toDouble(), value.z.toDouble())
    }

    fun node3dGetGlobalPosition(node: Long): Vector3 =
        node3dGetVector3(node, ::kanama_ios_godot_node3d_get_global_position)

    fun node3dSetGlobalPosition(node: Long, value: Vector3) {
        kanama_ios_godot_node3d_set_global_position(node, value.x.toDouble(), value.y.toDouble(), value.z.toDouble())
    }

    fun node3dRotateY(node: Long, angle: Double) {
        kanama_ios_godot_node3d_rotate_y(node, angle)
    }

    fun canvasItemGetViewportRect(objectHandle: Long): Rect2 =
        memScoped {
            val x = alloc<DoubleVarCompat>()
            val y = alloc<DoubleVarCompat>()
            val width = alloc<DoubleVarCompat>()
            val height = alloc<DoubleVarCompat>()
            kanama_ios_godot_canvas_item_get_viewport_rect(
                objectHandle,
                x.ptr,
                y.ptr,
                width.ptr,
                height.ptr,
            )
            Rect2(Vector2(x.value, y.value), Vector2(width.value, height.value))
        }

    fun canvasItemGetLocalMousePosition(objectHandle: Long): Vector2 =
        memScoped {
            val x = alloc<DoubleVarCompat>()
            val y = alloc<DoubleVarCompat>()
            kanama_ios_godot_canvas_item_get_local_mouse_position(objectHandle, x.ptr, y.ptr)
            Vector2(x.value, y.value)
        }

    fun canvasItemHide(objectHandle: Long) {
        kanama_ios_godot_canvas_item_hide(objectHandle)
    }

    fun canvasItemShow(objectHandle: Long) {
        kanama_ios_godot_canvas_item_show(objectHandle)
    }

    fun canvasItemSetModulate(objectHandle: Long, color: Color) {
        kanama_ios_godot_canvas_item_set_modulate(
            objectHandle,
            color.r.toDouble(),
            color.g.toDouble(),
            color.b.toDouble(),
            color.a.toDouble(),
        )
    }

    fun packedSceneInstantiate(packedScene: Long, editState: Long): Long =
        kanama_ios_godot_packed_scene_instantiate(packedScene, editState)

    fun gpuParticles2dSetEmitting(particles: Long, value: Boolean) {
        kanama_ios_godot_gpu_particles2d_set_emitting(particles, if (value) 1 else 0)
    }

    fun gpuParticles2dSetLifetime(particles: Long, value: Double) {
        kanama_ios_godot_gpu_particles2d_set_lifetime(particles, value)
    }

    fun gpuParticles2dRestart(particles: Long, keepSeed: Boolean) {
        kanama_ios_godot_gpu_particles2d_restart(particles, if (keepSeed) 1 else 0)
    }

    fun gpuParticles3dSetEmitting(particles: Long, value: Boolean) {
        kanama_ios_godot_gpu_particles3d_set_emitting(particles, if (value) 1 else 0)
    }

    fun gpuParticles3dRestart(particles: Long, keepSeed: Boolean) {
        kanama_ios_godot_gpu_particles3d_restart(particles, if (keepSeed) 1 else 0)
    }

    fun collisionShape3dSetDisabled(shape: Long, disabled: Boolean) {
        kanama_ios_godot_collision_shape3d_set_disabled(shape, if (disabled) 1 else 0)
    }

    fun resourceLoaderLoad(path: String, typeHint: String): Long =
        kanama_ios_godot_resource_loader_load(path, typeHint)

    fun sprite2dSetTexture(sprite: Long, texture: Long) {
        kanama_ios_godot_sprite2d_set_texture(sprite, texture)
    }

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

    fun objectDisconnect(sourceObject: Long, signalName: String, targetObject: Long, method: String): Int =
        kanama_ios_godot_object_disconnect(sourceObject, signalName, targetObject, method)

    fun objectConnectCallable(sourceObject: Long, signalName: String, callbackId: Long, flags: Long): Long =
        kanama_ios_godot_object_connect_callable(sourceObject, signalName, callbackId, flags)

    fun objectDisconnectCallable(sourceObject: Long, signalName: String, callbackId: Long): Int =
        kanama_ios_godot_object_disconnect_callable(sourceObject, signalName, callbackId)

    fun tweenTweenPropertyVector2(tween: Long, target: Long, property: String, x: Double, y: Double, duration: Double): Long =
        kanama_ios_godot_tween_tween_property_vector2(tween, target, property, x, y, duration)

    fun tweenTweenPropertyColor(tween: Long, target: Long, property: String, r: Double, g: Double, b: Double, a: Double, duration: Double): Long =
        kanama_ios_godot_tween_tween_property_color(tween, target, property, r, g, b, a, duration)

    fun tweenSetParallel(tween: Long, parallel: Int): Long =
        kanama_ios_godot_tween_set_parallel(tween, parallel)

    fun tweenTweenCallback(tween: Long, target: Long, method: String): Long =
        kanama_ios_godot_tween_tween_callback(tween, target, method)

    fun tweenTweenMethod(tween: Long, target: Long, method: String, from: Double, to: Double, duration: Double): Long =
        kanama_ios_godot_tween_tween_method(tween, target, method, from, to, duration)

    fun propertyTweenerFromColor(tweener: Long, r: Double, g: Double, b: Double, a: Double): Long =
        kanama_ios_godot_property_tweener_from_color(tweener, r, g, b, a)

    fun tweenKill(tween: Long) {
        kanama_ios_godot_tween_kill(tween)
    }

    fun tweenerSetTrans(tweener: Long, trans: Long): Long =
        kanama_ios_godot_tweener_set_trans(tweener, trans)

    fun tweenerSetEase(tweener: Long, ease: Long): Long =
        kanama_ios_godot_tweener_set_ease(tweener, ease)

    fun viewportGetVisibleRect(viewport: Long): Rect2 =
        memScoped {
            val x = alloc<DoubleVarCompat>()
            val y = alloc<DoubleVarCompat>()
            val w = alloc<DoubleVarCompat>()
            val h = alloc<DoubleVarCompat>()
            kanama_ios_godot_viewport_get_visible_rect(viewport, x.ptr, y.ptr, w.ptr, h.ptr)
            Rect2(Vector2(x.value, y.value), Vector2(w.value, h.value))
        }

    private fun labelSetTextBind(): Long {
        if (labelSetTextBind == 0L) {
            labelSetTextBind = kanama_ios_godot_get_method_bind(
                "Label",
                "set_text",
                LABEL_SET_TEXT_HASH,
            )
        }
        return labelSetTextBind
    }

    private inline fun node3dGetVector3(
        node: Long,
        getter: (Long, kotlinx.cinterop.CPointer<DoubleVarCompat>?, kotlinx.cinterop.CPointer<DoubleVarCompat>?, kotlinx.cinterop.CPointer<DoubleVarCompat>?) -> Unit,
    ): Vector3 =
        memScoped {
            val x = alloc<DoubleVarCompat>()
            val y = alloc<DoubleVarCompat>()
            val z = alloc<DoubleVarCompat>()
            getter(node, x.ptr, y.ptr, z.ptr)
            Vector3(x.value, y.value, z.value)
        }
}

@OptIn(ExperimentalForeignApi::class)
private typealias DoubleVarCompat = kotlinx.cinterop.DoubleVar

// Registry backing lambda/bound signal connections. A connection registers its
// callback here and passes the integer id to the C shim, which binds it to a
// custom Godot Callable. When the signal fires the shim calls back into
// kanamaIosRuntimeDispatchCallable; when Godot drops the connection it calls
// kanamaIosRuntimeReleaseCallable so the entry can be collected.
internal object IosCallableRegistry {
    private var nextId = 1L
    private val callbacks = HashMap<Long, (List<Any?>) -> Unit>()

    fun register(callback: (List<Any?>) -> Unit): Long {
        val id = nextId++
        callbacks[id] = callback
        return id
    }

    fun dispatch(callbackId: Long, args: List<Any?>) {
        callbacks[callbackId]?.invoke(args)
    }

    fun release(callbackId: Long) {
        callbacks.remove(callbackId)
    }
}

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_dispatch_callable")
fun kanamaIosRuntimeDispatchCallable(
    callbackId: Long,
    argumentCount: Int,
    arg0: Long,
    arg1: Long,
    arg2: Long,
    arg3: Long,
) {
    val handles = longArrayOf(arg0, arg1, arg2, arg3)
    val count = argumentCount.coerceIn(0, handles.size)
    val args = ArrayList<Any?>(count)
    for (i in 0 until count) {
        val handle = handles[i]
        args.add(if (handle != 0L) GodotObject(handle) else null)
    }
    IosCallableRegistry.dispatch(callbackId, args)
}

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_release_callable")
fun kanamaIosRuntimeReleaseCallable(callbackId: Long) {
    IosCallableRegistry.release(callbackId)
}

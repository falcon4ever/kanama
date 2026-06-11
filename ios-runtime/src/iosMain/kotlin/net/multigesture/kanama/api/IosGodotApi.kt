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
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
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
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_tweener_set_ease
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_tweener_set_trans
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_viewport_get_visible_rect
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import kotlin.coroutines.CoroutineContext
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

// KANAMA-IOS-HANDWRITTEN: KanamaScope bridges Godot's main thread to Kotlin coroutines; not generatable from extension_api.json.
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

// KANAMA-IOS-HANDWRITTEN: MainThread.post is a no-op shim; on iOS main thread dispatch is handled by Godot's frame loop, not a JVM executor.
object MainThread {
    fun post(action: () -> Unit) {
        action()
    }
}

open class GodotObject(
    val handle: MemorySegment,
) {
    constructor(handle: Long) : this(MemorySegment.ofAddress(handle))

    fun requireOpenHandle(): MemorySegment = handle

    fun isClass(className: String): Boolean =
        className.isNotBlank() && IosGodot.objectIsClass(handle.address(), className)

    // KANAMA-IOS-HANDWRITTEN: signal/connect/emitSignal/await use the custom GDExtension
    // Callable + IosCallableRegistry (lambda/bound dispatch); bespoke runtime, not generated.
    fun signal(name: String): GodotSignal =
        GodotSignal(this, name)

    // KANAMA-IOS-STUB: should dispatch via the Variant Object.call path; not wired yet. Backlog.
    fun call(method: String, vararg args: Any?): Any? =
        null

    // KANAMA-IOS-STUB: should call Object.set_deferred via Variant dispatch; not wired yet. Backlog.
    fun setDeferred(property: String, value: Any?) {
    }

    fun connect(signalName: String, target: GodotObject, method: String, flags: Long = CONNECT_DEFAULT): Long =
        IosGodot.objectConnect(handle.address(), signalName, target.handle.address(), method, flags)

    // KANAMA-IOS-STUB: should call Object.disconnect; not wired yet. Backlog.
    fun disconnect(signalName: String, target: GodotObject, method: String) {
    }

    // KANAMA-IOS-STUB: connectBound should call Object.connect with bound args Callable; not wired yet. Backlog.
    fun connectBound(
        signalName: String,
        target: GodotObject,
        method: String,
        boundArgs: List<Any?>,
        flags: Long = CONNECT_DEFAULT,
    ): Long = 0L

    // KANAMA-IOS-STUB: should call Object.disconnect; not wired yet. Backlog.
    fun disconnectBound(signalName: String, target: GodotObject, method: String, boundArgs: List<Any?>) {
    }

    fun emitSignal(signalName: String, value: Int): Int =
        IosGodot.objectEmitSignalInt(handle.address(), signalName, value.toLong())

    fun emitSignal(signalName: String, value: Long): Int =
        IosGodot.objectEmitSignalInt(handle.address(), signalName, value)

    fun emitSignal(signalName: String, value: Vector2i): Int =
        IosGodot.objectEmitSignalVector2i(handle.address(), signalName, value.x.toLong(), value.y.toLong())

    fun emitSignal(signalName: String, vararg args: Any?) {
        val first = args.firstOrNull()
        when (first) {
            is Int -> emitSignal(signalName, first)
            is Long -> emitSignal(signalName, first)
            is Vector2i -> emitSignal(signalName, first)
        }
    }

    // KANAMA-IOS-HANDWRITTEN: AutoCloseable no-op base; Godot object lifetime is managed externally, not by Kotlin's close().
    open fun close() {
    }

    companion object {
        const val CONNECT_DEFAULT = 0L
        const val CONNECT_ONE_SHOT = 4L

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
        return SignalConnection()
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

class SignalConnection internal constructor() : AutoCloseable {
    // KANAMA-IOS-STUB: should carry the real connect() return Error; hardcoded OK. Backlog.
    val error: Long = 0L

    // KANAMA-IOS-STUB: close() should disconnect the connection; not wired yet. Backlog.
    override fun close() {
    }
}

open class StaticBody3D(handle: MemorySegment) : Node3D(handle)

class SceneTree(handle: MemorySegment) : Node(handle) {
    fun quit(exitCode: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(quitBind, handle, exitCode)
    }

    fun reloadCurrentScene(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(reloadCurrentSceneBind, handle)

    companion object {
        private val quitBind by lazy { ObjectCalls.getMethodBind("SceneTree", "quit", 1995695955L) }
        private val reloadCurrentSceneBind by lazy {
            ObjectCalls.getMethodBind("SceneTree", "reload_current_scene", 166280745L)
        }

        suspend fun delaySeconds(seconds: Double) {
            delay((seconds * 1000.0).toLong().coerceAtLeast(0L))
        }
    }
}

class AudioStreamPlayer(handle: MemorySegment) : Node(handle) {
    fun setStreamFromPath(path: String) {
        val stream = IosGodot.resourceLoaderLoad(path, "")
        if (stream != 0L) {
            IosGodot.audioStreamPlayerSetStream(handle.address(), stream)
        }
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

    companion object {
        fun create(): AudioStreamPlayer =
            AudioStreamPlayer(MemorySegment.ofAddress(IosGodot.constructObject("AudioStreamPlayer")))
    }
}

// KANAMA-IOS-HANDWRITTEN: Tweener/PropertyTweener/Tween use the Variant tween_property path
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

class PropertyTweener(handle: MemorySegment) : Tweener(handle)

class Tween(handle: MemorySegment) : GodotObject(handle) {
    fun setParallel(parallel: Boolean): Tween {
        IosGodot.tweenSetParallel(handle.address(), if (parallel) 1 else 0)
        return this
    }

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
        const val EASE_OUT = 1L
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

        fun from(value: GodotObject): InputEventMouseButton? =
            if (value.isClass("InputEventMouseButton")) InputEventMouseButton(value.handle)
            else if (value.isClass("InputEventScreenTouch")) InputEventMouseButton(value.handle)
            else null
    }
}

// KANAMA-IOS-HANDWRITTEN: Input is the bespoke singleton glue (getSingleton + cached binds);
// getAxis/isActionJustPressed are real ptrcalls. Kept hand-written (not a generated wrapper).
object Input {
    private val singleton: MemorySegment by lazy { ObjectCalls.getSingleton("Input") }
    private val getAxisBind by lazy { ObjectCalls.getMethodBind("Input", "get_axis", 1958752504L) }
    private val isActionJustPressedBind by lazy {
        ObjectCalls.getMethodBind("Input", "is_action_just_pressed", 1558498928L)
    }

    // KANAMA-IOS-STUB: cosmetic; needs Texture2D arg marshalling to call set_custom_mouse_cursor. Backlog.
    fun setCustomMouseCursor(texture: Texture2D?, shape: Long = 0L, hotspot: Vector2 = Vector2.ZERO) {
    }

    fun getAxis(negativeAction: String, positiveAction: String): Double =
        ObjectCalls.ptrcallWithTwoStringNameArgsRetDouble(getAxisBind, singleton, negativeAction, positiveAction)

    fun isActionJustPressed(action: String, exactMatch: Boolean = false): Boolean =
        ObjectCalls.ptrcallWithStringNameAndBoolArgRetBool(isActionJustPressedBind, singleton, action, exactMatch)
}

// KANAMA-IOS-HANDWRITTEN: pure-Kotlin math helpers (no Godot call). Bespoke utility.
object Mathf {
    fun abs(value: Double): Double = kotlin.math.abs(value)

    fun abs(value: Float): Float = kotlin.math.abs(value)

    fun cos(value: Double): Double = kotlin.math.cos(value)

    fun log(value: Double): Double = kotlin.math.ln(value)

    fun lerp(from: Double, to: Double, weight: Double): Double =
        from + (to - from) * weight

    fun lerpAngle(from: Double, to: Double, weight: Double): Double {
        val difference = ((to - from + PI) % (PI * 2.0)) - PI
        return from + difference * weight
    }

    fun clamp(value: Double, min: Double, max: Double): Double =
        value.coerceIn(min, max)
}

// KANAMA-IOS-HANDWRITTEN: ResourceLoader singleton glue over the C shim resource loader.
object ResourceLoader {
    fun load(path: String): Resource? =
        IosGodot.resourceLoaderLoad(path, "").takeIf { it != 0L }?.let {
            Resource(MemorySegment.ofAddress(it))
        }

    fun loadTexture2D(path: String): Texture2D? =
        IosGodot.resourceLoaderLoad(path, "Texture2D").takeIf { it != 0L }?.let {
            Texture2D(MemorySegment.ofAddress(it))
        }
}

// KANAMA-IOS-HANDWRITTEN: GD global helpers (rand*, print) — Kotlin/native impls, bespoke.
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
}

inline fun <reified T> GodotObject.kotlinScriptInstance(): T? =
    net.multigesture.kanama.ios.iosScriptInstanceForOwner(handle.address()) as? T

inline fun <reified T> Node.kotlinScriptInstance(): T? =
    GodotObject(handle).kotlinScriptInstance<T>()

@OptIn(ExperimentalForeignApi::class)
// KANAMA-IOS-HANDWRITTEN: thin cinterop facade over the C shim helpers used by the bespoke
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
        kanama_ios_godot_node2d_set_position(node, value.x, value.y)
    }

    fun node2dGetScale(node: Long): Vector2 =
        memScoped {
            val x = alloc<DoubleVarCompat>()
            val y = alloc<DoubleVarCompat>()
            kanama_ios_godot_node2d_get_scale(node, x.ptr, y.ptr)
            Vector2(x.value, y.value)
        }

    fun node2dSetScale(node: Long, value: Vector2) {
        kanama_ios_godot_node2d_set_scale(node, value.x, value.y)
    }

    fun node3dGetPosition(node: Long): Vector3 =
        node3dGetVector3(node, ::kanama_ios_godot_node3d_get_position)

    fun node3dSetPosition(node: Long, value: Vector3) {
        kanama_ios_godot_node3d_set_position(node, value.x, value.y, value.z)
    }

    fun node3dGetRotation(node: Long): Vector3 =
        node3dGetVector3(node, ::kanama_ios_godot_node3d_get_rotation)

    fun node3dSetRotation(node: Long, value: Vector3) {
        kanama_ios_godot_node3d_set_rotation(node, value.x, value.y, value.z)
    }

    fun node3dGetScale(node: Long): Vector3 =
        node3dGetVector3(node, ::kanama_ios_godot_node3d_get_scale)

    fun node3dSetScale(node: Long, value: Vector3) {
        kanama_ios_godot_node3d_set_scale(node, value.x, value.y, value.z)
    }

    fun node3dGetGlobalPosition(node: Long): Vector3 =
        node3dGetVector3(node, ::kanama_ios_godot_node3d_get_global_position)

    fun node3dSetGlobalPosition(node: Long, value: Vector3) {
        kanama_ios_godot_node3d_set_global_position(node, value.x, value.y, value.z)
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

    fun objectConnectCallable(sourceObject: Long, signalName: String, callbackId: Long, flags: Long): Long =
        kanama_ios_godot_object_connect_callable(sourceObject, signalName, callbackId, flags)

    fun tweenTweenPropertyVector2(tween: Long, target: Long, property: String, x: Double, y: Double, duration: Double): Long =
        kanama_ios_godot_tween_tween_property_vector2(tween, target, property, x, y, duration)

    fun tweenTweenPropertyColor(tween: Long, target: Long, property: String, r: Double, g: Double, b: Double, a: Double, duration: Double): Long =
        kanama_ios_godot_tween_tween_property_color(tween, target, property, r, g, b, a, duration)

    fun tweenSetParallel(tween: Long, parallel: Int): Long =
        kanama_ios_godot_tween_set_parallel(tween, parallel)

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

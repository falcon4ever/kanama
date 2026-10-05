package net.multigesture.kanama.api

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import net.multigesture.kanama.types.NodePath

/**
 * Base class for attachable scripts that want a typed wrapper for their own Godot object.
 *
 * Kotlin's `this` is the script object, not the Godot node or resource the script is attached to.
 * [self] is a non-owning wrapper around that Godot object, typed to the script's primary `attachTo`
 * class:
 * ```kotlin
 * @ScriptClass(attachTo = "Node")
 * class Main(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
 *     private val scoreTimer by node<Timer>("ScoreTimer")   // GDScript: @onready var score_timer: Timer = $ScoreTimer
 *     private val hud by script<Hud>("HUD")                  // the Kotlin script on the HUD node
 *     private val mobScene by preload<PackedScene>("res://mob.tscn")
 *
 *     @OnReady
 *     fun ready() {
 *         scoreTimer.start()
 *         launch {                                           // GDScript: await get_tree().create_timer(1.0).timeout
 *             wait(1.0)
 *             hud.showMessage("Go")
 *         }
 *     }
 * }
 * ```
 *
 * Written once for desktop, Android and iOS (task 133); the platform-bound parts go through the
 * internal `ScriptRuntime` seam. [self] and [selfAs] do not own the native object and must not free
 * it.
 */
abstract class KanamaScript<Self : Any>(
  val godotObject: GodotHandle,
  selfFactory: (GodotHandle) -> Self,
) {
  val self: Self = selfFactory(godotObject)

  /**
   * Keeps this script's runtime instance alive while this object is reachable, and holds the
   * cleanup that releases the owner reference once it is not (task 132: a script object on a
   * `RefCounted` keeps its owner alive, as in GDScript). Runtime-owned; set when the instance is
   * created.
   */
  internal var kanamaInstanceAnchor: Any? = null

  /** Wrap this script's Godot object as another compatible Kanama wrapper type. */
  inline fun <T> selfAs(ctor: (GodotHandle) -> T): T = ctor(godotObject)

  /**
   * Refresh the Godot inspector after exported property metadata changes.
   *
   * This is mainly useful from `@Tool` scripts whose visible properties, groups, or usage flags
   * depend on editor-time state.
   */
  fun notifyInspectorChanged() {
    GodotObject(godotObject).notifyPropertyListChanged()
  }

  /** True when the script is running inside the Godot editor. */
  fun isEditorHint(): Boolean = ScriptRuntime.isEditorHint()

  // ── Coroutines ──────────────────────────────────────────────────────────────────────────────

  private var scopeOrNull: CoroutineScope? = null
  private var scopeDisposed = false

  /**
   * This script's coroutine scope: it runs on the engine main thread, is created on first use, and
   * is cancelled when Godot frees the script instance (the object is freed or the script is
   * detached). An exception that escapes one of its coroutines is reported as a Godot script error
   * and does not cancel the others. [launch] is the short form of `scriptScope.launch`.
   */
  val scriptScope: CoroutineScope
    get() {
      scopeOrNull?.let {
        return it
      }
      val scope = ScriptRuntime.newScriptScope()
      if (scopeDisposed) scope.cancel()
      scopeOrNull = scope
      return scope
    }

  /**
   * Starts [block] as a coroutine of this script, the way a GDScript function that `await`s runs
   * on: it suspends at [wait], [nextFrame] or a signal `await`, and the rest runs on a later frame.
   * It stops when the script instance is freed.
   */
  fun launch(block: suspend CoroutineScope.() -> Unit): Job = scriptScope.launch(block = block)

  /**
   * Suspends for [seconds] on a `SceneTree` timer, as GDScript's
   * `await get_tree().create_timer(seconds).timeout`, with the same defaults: the timer keeps
   * running while the tree is paused ([processAlways] `= true`; pass `false` for a wait that pauses
   * with the game) and follows `Engine.time_scale` ([ignoreTimeScale] `= false`). The script's node
   * must be inside the tree.
   */
  suspend fun wait(seconds: Double, processAlways: Boolean = true, ignoreTimeScale: Boolean = false) {
    val node = ownerNode("wait")
    node.tree
      .createTimer(seconds, processAlways = processAlways, ignoreTimeScale = ignoreTimeScale)
      .use { timer -> timer.signal(SceneTreeTimer.Signals.timeout).await(node) }
  }

  /** Suspends until the next engine frame (GDScript: `await get_tree().process_frame`). */
  suspend fun nextFrame() {
    MainThread.awaitNextFrame()
  }

  // ── Signals ─────────────────────────────────────────────────────────────────────────────────

  /**
   * Connects [callback] to this signal, bound to this script's object: GDScript's
   * `timer.timeout.connect(func(): …)` is `timer.timeout.connect { … }`. Godot drops the connection
   * (and Kanama releases the lambda) when this script's object or the emitter is freed; close the
   * returned [SignalConnection] to disconnect earlier. Task 134.
   */
  fun Signal0.connect(callback: () -> Unit): SignalConnection = connect(GodotObject(godotObject), callback)

  /** [Signal0.connect] bound to this script's object, with [flags] (`ConnectFlags.ONE_SHOT`, `DEFERRED`). */
  fun Signal0.connect(flags: GodotObject.ConnectFlags, callback: () -> Unit): SignalConnection =
    connect(GodotObject(godotObject), flags, callback)

  /** Connects [callback] bound to this script's object (see `Signal0.connect`). */
  fun <A> Signal1<A>.connect(callback: (A) -> Unit): SignalConnection =
    connect(GodotObject(godotObject), callback)

  /** Connects [callback] bound to this script's object, with [flags]. */
  fun <A> Signal1<A>.connect(flags: GodotObject.ConnectFlags, callback: (A) -> Unit): SignalConnection =
    connect(GodotObject(godotObject), flags, callback)

  /** Connects [callback] bound to this script's object (see `Signal0.connect`). */
  fun <A, B> Signal2<A, B>.connect(callback: (A, B) -> Unit): SignalConnection =
    connect(GodotObject(godotObject), callback)

  /** Connects [callback] bound to this script's object, with [flags]. */
  fun <A, B> Signal2<A, B>.connect(flags: GodotObject.ConnectFlags, callback: (A, B) -> Unit): SignalConnection =
    connect(GodotObject(godotObject), flags, callback)

  /** Connects [callback] bound to this script's object (see `Signal0.connect`). */
  fun <A, B, C> Signal3<A, B, C>.connect(callback: (A, B, C) -> Unit): SignalConnection =
    connect(GodotObject(godotObject), callback)

  /** Connects [callback] bound to this script's object, with [flags]. */
  fun <A, B, C> Signal3<A, B, C>.connect(flags: GodotObject.ConnectFlags, callback: (A, B, C) -> Unit): SignalConnection =
    connect(GodotObject(godotObject), flags, callback)

  /** Connects [callback] bound to this script's object (see `Signal0.connect`). */
  fun <A, B, C, D> Signal4<A, B, C, D>.connect(callback: (A, B, C, D) -> Unit): SignalConnection =
    connect(GodotObject(godotObject), callback)

  /** Connects [callback] bound to this script's object, with [flags]. */
  fun <A, B, C, D> Signal4<A, B, C, D>.connect(flags: GodotObject.ConnectFlags, callback: (A, B, C, D) -> Unit): SignalConnection =
    connect(GodotObject(godotObject), flags, callback)

  /** Connects [callback] bound to this script's object (see `Signal0.connect`). */
  fun <A, B, C, D, E> Signal5<A, B, C, D, E>.connect(callback: (A, B, C, D, E) -> Unit): SignalConnection =
    connect(GodotObject(godotObject), callback)

  /** Connects [callback] bound to this script's object, with [flags]. */
  fun <A, B, C, D, E> Signal5<A, B, C, D, E>.connect(flags: GodotObject.ConnectFlags, callback: (A, B, C, D, E) -> Unit): SignalConnection =
    connect(GodotObject(godotObject), flags, callback)

  /**
   * Cancels every coroutine this script has running; the scope stays usable, so a later [launch]
   * starts fresh (for example after the node re-enters the tree).
   */
  fun cancelCoroutines() {
    scopeOrNull?.coroutineContext?.cancelChildren()
  }

  /** The free path (desktop `ScriptBridge.siFree`, iOS `freeScriptInstance`): ends the scope. */
  internal fun disposeScriptScope() {
    scopeDisposed = true
    scopeOrNull?.cancel()
  }

  /**
   * Counts the `_ready` cycles of this script's node; the [node] and [script] delegates re-resolve
   * when it changes, as GDScript re-runs its `@onready` initializers on every `_ready` (including
   * the one `request_ready()` schedules).
   */
  internal var readyGeneration = 0
    private set

  /**
   * Desktop and Android, on `NOTIFICATION_ENTER_TREE` (before `_ready`): a node that is not ready
   * yet is about to run `_ready`, so a new ready cycle starts.
   */
  internal fun onEnterTree() {
    if (!ownerNode("ready").isNodeReady()) readyGeneration++
  }

  /** iOS, just before the runtime dispatches `_ready`: a new ready cycle starts. */
  internal fun onReadyCycle() {
    readyGeneration++
  }

  // ── Node, script and resource access ────────────────────────────────────────────────────────

  /**
   * The node at [path] relative to this script's node, as a `T`: GDScript's
   * `@onready var timer: Timer = $ScoreTimer`.
   *
   * Resolved on first read, which must come once the node is ready (in `@OnReady` or later), and
   * cached until the next `_ready` (GDScript re-runs `@onready` on every `_ready`, including one
   * `request_ready()` schedules). A read before ready, a missing node or a node of another class
   * throws an `IllegalStateException` naming the property, the path and the class found.
   */
  inline fun <reified T : Node> node(path: String): ReadOnlyProperty<Any?, T> =
    nodeDelegate(NodePath(path), T::class)

  /** [node] for a [NodePath]. */
  inline fun <reified T : Node> node(path: NodePath): ReadOnlyProperty<Any?, T> =
    nodeDelegate(path, T::class)

  /**
   * The Kotlin script of type [T] attached to the node at [path] (GDScript:
   * `@onready var player: Player = $Player`, where `Player` is a script class). Resolved and cached
   * like [node]; a node without a Kotlin script, or with another one, throws.
   */
  inline fun <reified T : Any> script(path: String): ReadOnlyProperty<Any?, T> =
    scriptDelegate(NodePath(path), T::class)

  /** [script] for a [NodePath]. */
  inline fun <reified T : Any> script(path: NodePath): ReadOnlyProperty<Any?, T> =
    scriptDelegate(path, T::class)

  /**
   * The resource at [path] as a `T`, loaded once per process and shared by every script that
   * preloads it, as GDScript's `const BULLET = preload("res://bullet.tscn")`. Give an absolute
   * `res://` or `uid://` path. Loaded on first read; a missing file or another resource class
   * throws. The resource stays loaded until the engine shuts down: do not `close()` it.
   */
  inline fun <reified T : Resource> preload(path: String): ReadOnlyProperty<Any?, T> =
    preloadDelegate(path, T::class)

  @PublishedApi
  internal fun <T : Node> nodeDelegate(path: NodePath, type: KClass<T>): ReadOnlyProperty<Any?, T> =
    NodeDelegate(this, path, type)

  @PublishedApi
  internal fun <T : Any> scriptDelegate(path: NodePath, type: KClass<T>): ReadOnlyProperty<Any?, T> =
    ScriptDelegate(this, path, type)

  @PublishedApi
  internal fun <T : Resource> preloadDelegate(path: String, type: KClass<T>): ReadOnlyProperty<Any?, T> =
    PreloadDelegate(path, type)

  private var ownerNodeOrNull: Node? = null

  /** This script's object as a [Node]; [use] names the caller in the error when it is not one. */
  internal fun ownerNode(use: String): Node {
    ownerNodeOrNull?.let {
      return it
    }
    val node =
      (self as? Node)
        ?: GodotObject(godotObject).let { owner ->
          check(owner.isClass("Node")) {
            "${scriptName()}.$use needs a Node, but the script is attached to a ${owner.getClassName()}"
          }
          Node(godotObject)
        }
    ownerNodeOrNull = node
    return node
  }

  internal fun scriptName(): String = this::class.simpleName ?: "KanamaScript"
}

package net.multigesture.kanama.api

import kotlin.coroutines.resume
import kotlin.properties.ReadOnlyProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.web.KanamaWebScript

/**
 * The Web script base (task 142 moved it out of WebGodotApi.kt): the same constructor, `self`,
 * coroutine scope, typed-signal connects and node/script/preload delegates as desktop's
 * KanamaScript, over the Web script lifetime of [KanamaWebScript].
 */
abstract class KanamaScript<T : GodotObject>(
  val godotObject: GodotHandle,
  wrapper: (GodotHandle) -> T,
) : KanamaWebScript(godotObject) {
  // Public as on desktop, Android and iOS (task 133 C: `Autoloads.Events.self` is the autoload node).
  val self: T = wrapper(godotObject)

  inline fun <R> selfAs(ctor: (GodotHandle) -> R): R = ctor(godotObject)

  // The script coroutine scope (task 133), as on desktop, Android and iOS. Scripts are constructed
  // inside their own owner scope (WebInstanceRegistry.construct), so the owner is captured here and the
  // scope, created on first use, binds to this script and not to whichever callback launches.
  private val scopeOwner: Int = WebFrameScheduler.currentOwnerOrZero()
  private var scopeOrNull: KanamaScope? = null
  private var scopeDisposed = false

  /**
   * This script's coroutine scope: it runs on the frame scheduler, is created on first use, and is
   * cancelled when the script object is freed. [launch] is the short form of `scriptScope.launch`.
   */
  val scriptScope: CoroutineScope
    get() {
      scopeOrNull?.let {
        return it
      }
      val scope = KanamaScope(scopeOwner)
      if (scopeDisposed) scope.cancel()
      scopeOrNull = scope
      return scope
    }

  /** Starts [block] as a coroutine of this script; it stops when the script object is freed. */
  fun launch(block: suspend CoroutineScope.() -> Unit): Job = scriptScope.launch(block = block)

  /**
   * Suspends for [seconds] of frame time (on Web, the frame scheduler's `SceneTree.delaySeconds`).
   * The parameters mirror native `wait`; like Web `SceneTree.createTimer`, only the GDScript
   * defaults (`processAlways = true`, `ignoreTimeScale = false`) are supported.
   */
  suspend fun wait(seconds: Double, processAlways: Boolean = true, ignoreTimeScale: Boolean = false) {
    require(processAlways) { "Web wait supports only processAlways = true" }
    require(!ignoreTimeScale) { "Web wait supports only ignoreTimeScale = false" }
    SceneTree.delaySeconds(seconds)
  }

  /** Suspends until the next frame. */
  suspend fun nextFrame() {
    suspendCancellableCoroutine { continuation ->
      MainThread.post { if (continuation.isActive) continuation.resume(Unit) }
    }
  }

  // Typed signals bound to this script's object (task 134 D4), as on desktop, Android and iOS.

  /** Connects [callback] bound to this script's object: `timer.timeout.connect { … }`. */
  fun Signal0.connect(callback: () -> Unit): SignalConnection = connect(GodotObject(godotObject), callback)

  /** [Signal0.connect] bound to this script's object, with [flags]. */
  fun Signal0.connect(flags: GodotObject.ConnectFlags, callback: () -> Unit): SignalConnection =
    connect(GodotObject(godotObject), flags, callback)

  /** Connects [callback] bound to this script's object: `area.bodyEntered.connect { body -> … }`. */
  fun <A> Signal1<A>.connect(callback: (A) -> Unit): SignalConnection = connect(GodotObject(godotObject), callback)

  /** [Signal1.connect] bound to this script's object, with [flags]. */
  fun <A> Signal1<A>.connect(flags: GodotObject.ConnectFlags, callback: (A) -> Unit): SignalConnection =
    connect(GodotObject(godotObject), flags, callback)

  /** Cancels every coroutine this script has running; a later [launch] starts fresh. */
  fun cancelCoroutines() {
    scopeOrNull?.coroutineContext?.cancelChildren()
  }

  internal override fun onFree() {
    scopeDisposed = true
    scopeOrNull?.cancel()
  }

  // The node, script and preload delegates, members as on desktop (KanamaScript.kt), so a shared
  // script needs no Web-only import (task 142). They resolve in WebScriptDelegates.kt.

  /**
   * The node at [path] relative to this script's node, as a `T`: GDScript's
   * `@onready var timer: Timer = $ScoreTimer`. Resolved on its first read after `_ready` and
   * cached until the next `_ready`.
   */
  inline fun <reified T : Node> node(path: String): ReadOnlyProperty<Any?, T> =
    WebNodeDelegate(this, path, T::class)

  /** [node] for a [NodePath]. */
  inline fun <reified T : Node> node(path: NodePath): ReadOnlyProperty<Any?, T> =
    WebNodeDelegate(this, path.path, T::class)

  /** The Kotlin script of type [T] on the node at [path]; resolved and cached like [node]. */
  inline fun <reified T : Any> script(path: String): ReadOnlyProperty<Any?, T> =
    WebScriptDelegate(this, path, T::class)

  /** [script] for a [NodePath]. */
  inline fun <reified T : Any> script(path: NodePath): ReadOnlyProperty<Any?, T> =
    WebScriptDelegate(this, path.path, T::class)

  /** The resource at [path] as a `T`, loaded once per page and kept: GDScript's `preload`. */
  inline fun <reified T : Resource> preload(path: String): ReadOnlyProperty<Any?, T> =
    WebPreloadDelegate(path, T::class)
}

@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.InitialGodotCallDescriptors as D
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2i

/**
 * Hand-shaped facades for families the browser cannot host or the compatibility renderer ignores
 * (tps-demo, task 60i), plus the import-compat aliases the ported corpus spells. Web builds are
 * single-player: ENet needs UDP sockets, so the multiplayer surface reports a local authoritative
 * session (peer 1, no remote peers) and the lobby's connect paths fail the way the demo already
 * handles. The display/renderer settings that the compatibility renderer ignores are no-ops.
 *
 * `Window` and the ray query (`WebPhysicsQuery.kt`) are the two hand-shaped classes that dispatch
 * opcodes directly; `WEB_HANDSHAPED` in `scripts/generate_web_wrappers.py` names them and the
 * generator's `--check` fails on any other hand-written dispatch.
 */

// Liveness: `Node.isInsideTree()` and `GodotObject.isQueuedForDeletion()` are generated members
// since protocol 26 (task 64 parcel 6, opcodes 319/320) -- the earlier facades that collapsed both
// onto GD.isInstanceValid are gone.

/** Web-only erasure helper for the ported corpus (no desktop counterpart; stays an extension). */
fun GodotObject.asObject(): GodotObject = this

/** Import-compat alias for shared demo sources; mirrors the [PhysicsBody3D] companion (task 64). */
object BodyAxis {
  const val ANGULAR_X: Long = PhysicsBody3D.BODY_AXIS_ANGULAR_X
  const val ANGULAR_Y: Long = PhysicsBody3D.BODY_AXIS_ANGULAR_Y
  const val ANGULAR_Z: Long = PhysicsBody3D.BODY_AXIS_ANGULAR_Z
}

object Time {
  private val origin = kotlin.time.TimeSource.Monotonic.markNow()

  /** Web adaptation: a Kotlin monotonic clock (only relative time is consumed). */
  fun getTicksMsec(): Long = origin.elapsedNow().inWholeMilliseconds
}

/** Same owned-handle idiom for the settings menu's radio groups. */
inline fun <R> ButtonGroup.use(block: (ButtonGroup) -> R): R {
  try {
    return block(this)
  } finally {
    close()
  }
}

// ---------------------------------------------------------------------------
// Multiplayer facade.
// ---------------------------------------------------------------------------

/**
 * `AutoCloseable` like desktop's `RefCounted`, so the shared demo sources' `peer.use { }` resolves
 * to the stdlib `use` on both backends without an import.
 */
open class MultiplayerPeer internal constructor() : AutoCloseable {
  open fun closeConnection() = Unit

  override fun close() = Unit
}

class OfflineMultiplayerPeer internal constructor() : MultiplayerPeer() {
  companion object {
    fun create(): OfflineMultiplayerPeer = OfflineMultiplayerPeer()
  }
}

class ENetMultiplayerPeer internal constructor() : MultiplayerPeer() {
  /** Always fails on Web (no UDP sockets); the lobby surfaces the error to the player. */
  fun createServer(@Suppress("UNUSED_PARAMETER") port: Int): Long = ERR_UNAVAILABLE

  fun createClient(
    @Suppress("UNUSED_PARAMETER") address: String,
    @Suppress("UNUSED_PARAMETER") port: Int,
  ): Long = ERR_UNAVAILABLE

  companion object {
    private const val ERR_UNAVAILABLE = 35L

    fun create(): ENetMultiplayerPeer = ENetMultiplayerPeer()
  }
}

class MultiplayerAPI internal constructor() {
  fun isServer(): Boolean = true

  /** Owned-handle idiom of the shared demo helpers (`withMultiplayer` closes what it got); a no-op here. */
  fun close() = Unit

  fun getUniqueId(): Int = 1

  fun getPeers(): List<Int> = emptyList()

  fun getRemoteSenderId(): Int = 0

  fun getMultiplayerPeer(): MultiplayerPeer? = peer

  var multiplayerPeer: MultiplayerPeer?
    get() = peer
    set(value) {
      peer = value
    }

  /** No remote peers ever connect, so these signals never fire; connects are no-ops. */
  fun signal(@Suppress("UNUSED_PARAMETER") name: String): InertSignal = InertSignal

  object Signals {
    const val peerConnected = "peer_connected"
    const val peerDisconnected = "peer_disconnected"
    const val connectedToServer = "connected_to_server"
    const val connectionFailed = "connection_failed"
    const val serverDisconnected = "server_disconnected"
  }

  private companion object {
    var peer: MultiplayerPeer? = null
  }
}

/** A signal that can never fire on Web; connecting to it is deliberately inert. */
object InertSignal {
  fun connect(
    @Suppress("UNUSED_PARAMETER") target: GodotObject,
    @Suppress("UNUSED_PARAMETER") argumentCount: Int = 0,
    @Suppress("UNUSED_PARAMETER") handler: (List<Any?>) -> Unit,
  ) = Unit
}

object SceneMultiplayer {
  fun fromApi(api: MultiplayerAPI?): SceneMultiplayerHandle? = api?.let { SceneMultiplayerHandle }
}

object SceneMultiplayerHandle {
  var serverRelay: Boolean = false
}

private val multiplayerApi = MultiplayerAPI()

/** Nullable to match the desktop shape, so ported call sites keep their `?.` chains. */
fun Node.getMultiplayer(): MultiplayerAPI? = multiplayerApi

/** Replication node; with a single local peer there is nothing to synchronize. */
class MultiplayerSynchronizer(godotObject: GodotHandle) : Node(godotObject) {
  var publicVisibility: Boolean = true

  fun setMultiplayerAuthority(@Suppress("UNUSED_PARAMETER") id: Int) = Unit

  fun getMultiplayerAuthority(): Int = 1
}

object IP {
  // KANAMA-BLOCKED(since:2026-07-27, webcall:IP.get_local_addresses): Web dispatches no IP call
  /** No socket surface on Web; the lobby falls back to its "this device" copy. */
  fun getLocalAddresses(): List<String> = emptyList()
}

// ---------------------------------------------------------------------------
// Display / renderer settings. The compatibility renderer ignores most of the
// tps graphics menu, so those writes are no-ops rather than fake successes.
// ---------------------------------------------------------------------------

/**
 * Window. Two shapes share the class:
 * - **Handle-backed** (task 64 tier 3): `Window(getTree().getRoot())` wraps the tracked root
 *   window, and [setMode] / [getMode] (and the [mode] property) are real engine calls --
 *   FullScreenHandler's F11 toggle reads the mode the engine reports.
 * - **Handle-less facade** ([Node.getWindow]): the tps settings menu's target. Mode and 3D
 *   scaling are fixed by the browser canvas there, so those writes are mirrored Kotlin-side and
 *   never reach the engine (the pre-existing facade contract, documented here and unchanged).
 */
class Window internal constructor() {
  private var windowHandle: GodotHandle? = null

  /** Desktop's handle-taking constructor: wrap a tracked Window handle (the tree root). */
  constructor(godotObject: GodotHandle) : this() {
    windowHandle = godotObject
  }

  /** Facade mirror for the handle-less window; unused when a handle is present. */
  private var facadeMode: Long = MODE_WINDOWED

  var mode: Long
    get() = getMode()
    set(value) = setMode(value)

  fun setMode(mode: Long) {
    val handle = windowHandle
    if (handle == null) facadeMode = mode
    else GodotBackendCalls.invokeLongArg(D.WINDOW_SET_MODE, handle.toBackendHandle(), mode)
  }

  fun getMode(): Long {
    val handle = windowHandle ?: return facadeMode
    return GodotBackendCalls.invokeNoArgsRetLong(D.WINDOW_GET_MODE, handle.toBackendHandle())
  }

  var scaling3dScale: Double = 1.0
  var scaling3dMode: Long = 0L
  var useTaa: Boolean = false
  var msaa3d: Long = 0L
  var screenSpaceAa: Long = 0L

  companion object {
    const val MODE_WINDOWED = 0L
    const val MODE_FULLSCREEN = 3L
    const val MODE_EXCLUSIVE_FULLSCREEN = 4L
    const val MODE_MAXIMIZED = 2L
  }
}

/**
 * The single browser window. `Node.getWindow()` is a generated member since task 64 parcel 8, and
 * hands this mirror back, so the shared demo sources call it exactly the way desktop does.
 */
internal val browserWindow = Window()

object DisplayServer {
  const val VSYNC_DISABLED = 0L
  const val VSYNC_ENABLED = 1L
  const val VSYNC_ADAPTIVE = 2L
  const val VSYNC_MAILBOX = 3L

  /** Web adaptation: the demo branches on "headless"; the browser is a real display. */
  fun getName(): String = "Web"

  fun windowSetVsyncMode(@Suppress("UNUSED_PARAMETER") mode: Long) = Unit

  fun windowGetVsyncMode(): Long = VSYNC_ENABLED

  /**
   * Structural limit (task 64 parcel 8): a browser page has no OS window to measure and no notch
   * to route around -- the canvas IS the usable area, and Godot's Web DisplayServer answers the
   * whole window for both. The one caller in the corpus (tps-demo's `SafeArea`, a mobile
   * notch/cutout helper) returns early on `OS.hasFeature("web")`, so these are never reached on
   * Web; they fail loud rather than inventing a rectangle a caller would act on.
   */
  fun windowGetSize(@Suppress("UNUSED_PARAMETER") windowId: Int = 0): Vector2i =
    unsupportedWebGameplayFamily("DisplayServer.window_get_size")

  fun getDisplaySafeArea(): Rect2i = unsupportedWebGameplayFamily("DisplayServer.get_display_safe_area")
}

// ---------------------------------------------------------------------------
// Resource loading facades.
// ---------------------------------------------------------------------------

/**
 * Threaded-load store behind `ResourceLoader.loadThreadedRequest` / `loadThreadedGetStatus…` /
 * `loadThreadedGet…` (generated members since task 64 parcel 8). The Web export is a `nothreads`
 * build, so a background load would never make progress: the request loads synchronously and
 * every later poll reports LOADED. The demo's loading screen still runs its normal status /
 * progress path, it just completes on the first poll.
 */
internal object ThreadedLoad {
  private val loaded = mutableMapOf<String, PackedScene?>()

  fun request(path: String) {
    loaded[path] = ResourceLoader.loadPackedScene(path)
  }

  fun status(path: String): Long =
    when {
      !loaded.containsKey(path) -> ResourceLoader.THREAD_LOAD_IN_PROGRESS
      loaded[path] == null -> ResourceLoader.THREAD_LOAD_FAILED
      else -> ResourceLoader.THREAD_LOAD_LOADED
    }

  fun take(path: String): PackedScene? = loaded[path]
}

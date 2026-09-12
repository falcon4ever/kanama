@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.InitialGodotCallDescriptors as D
import net.multigesture.kanama.backend.InternalKanamaBackendApi

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

private val browserWindow = Window()

fun Node.getWindow(): Window = browserWindow

object DisplayServer {
  const val VSYNC_DISABLED = 0L
  const val VSYNC_ENABLED = 1L
  const val VSYNC_ADAPTIVE = 2L
  const val VSYNC_MAILBOX = 3L

  /** Web adaptation: the demo branches on "headless"; the browser is a real display. */
  fun getName(): String = "Web"

  fun windowSetVsyncMode(@Suppress("UNUSED_PARAMETER") mode: Long) = Unit

  fun windowGetVsyncMode(): Long = VSYNC_ENABLED
}

/**
 * Renderer settings the compatibility renderer has no equivalent for. They stay inert rather than
 * pretending to apply; the settings menu still records the player's choice in the config file.
 */
object RenderingServerQuality {
  const val ENV_SDFGI_RAY_COUNT_32 = 2L
  const val ENV_SDFGI_RAY_COUNT_96 = 5L
  const val VOXEL_GI_QUALITY_LOW = 0L
  const val VOXEL_GI_QUALITY_HIGH = 1L
  const val ENV_SSAO_QUALITY_MEDIUM = 1L
  const val ENV_SSAO_QUALITY_HIGH = 2L
  const val ENV_SSIL_QUALITY_MEDIUM = 1L
  const val ENV_SSIL_QUALITY_HIGH = 2L
}

fun RenderingServer.getCurrentRenderingDriverName(): String = "opengl3"

fun RenderingServer.environmentSetSdfgiRayCount(@Suppress("UNUSED_PARAMETER") count: Long) = Unit

fun RenderingServer.voxelGiSetQuality(@Suppress("UNUSED_PARAMETER") quality: Long) = Unit

fun RenderingServer.environmentSetSsaoQuality(
  @Suppress("UNUSED_PARAMETER") quality: Long,
  @Suppress("UNUSED_PARAMETER") halfSize: Boolean,
  @Suppress("UNUSED_PARAMETER") adaptiveTarget: Double,
  @Suppress("UNUSED_PARAMETER") blurPasses: Int,
  @Suppress("UNUSED_PARAMETER") fadeOutFrom: Double,
  @Suppress("UNUSED_PARAMETER") fadeOutTo: Double,
) = Unit

fun RenderingServer.environmentSetSsilQuality(
  @Suppress("UNUSED_PARAMETER") quality: Long,
  @Suppress("UNUSED_PARAMETER") halfSize: Boolean,
  @Suppress("UNUSED_PARAMETER") adaptiveTarget: Double,
  @Suppress("UNUSED_PARAMETER") blurPasses: Int,
  @Suppress("UNUSED_PARAMETER") fadeOutFrom: Double,
  @Suppress("UNUSED_PARAMETER") fadeOutTo: Double,
) = Unit

/** The browser canvas owns fullscreen; the demo's own toggle stays inert. */
fun Viewport.setInputAsHandled() = Unit

/**
 * Screen-space effect toggles the compatibility renderer has no implementation for (SSAO,
 * volumetric fog): these stay inert rather than pretending to apply. `ssilEnabled` and
 * `sdfgiEnabled` are real (queued) members since protocol 24 — the engine ignores them on the
 * Compatibility renderer — so the shared DemoPage compiles; their getters are the setter-only
 * coverage markers of the generated wrapper.
 */
var Environment.ssaoEnabled: Boolean
  get() = false
  set(@Suppress("UNUSED_PARAMETER") value) = Unit

var Environment.volumetricFogEnabled: Boolean
  get() = false
  set(@Suppress("UNUSED_PARAMETER") value) = Unit

// ---------------------------------------------------------------------------
// Resource loading facades.
// ---------------------------------------------------------------------------

/** Baked lightmap data load (the LightmapGI settings path). */
fun ResourceLoader.loadLightmapGIData(path: String): LightmapGIData? =
  load(path, "LightmapGIData")?.let { LightmapGIData(it.handle) }

/**
 * Threaded-load facade. The Web export is a `nothreads` build, so a background load would never
 * make progress: the request loads synchronously and then reports LOADED. The demo's loading
 * screen still runs its normal status/progress path, it just completes on the first poll.
 */
object ThreadedLoad {
  const val THREAD_LOAD_IN_PROGRESS = 0L
  const val THREAD_LOAD_FAILED = 1L
  const val THREAD_LOAD_INVALID_RESOURCE = 2L
  const val THREAD_LOAD_LOADED = 3L

  private val loaded = mutableMapOf<String, PackedScene?>()

  fun request(path: String) {
    loaded[path] = ResourceLoader.loadPackedScene(path)
  }

  fun status(path: String): Long =
    when {
      !loaded.containsKey(path) -> THREAD_LOAD_IN_PROGRESS
      loaded[path] == null -> THREAD_LOAD_FAILED
      else -> THREAD_LOAD_LOADED
    }

  fun take(path: String): PackedScene? = loaded[path]
}

class ThreadedLoadStatus internal constructor(val status: Long, val progress: Double?)

fun ResourceLoader.loadThreadedRequest(
  path: String,
  @Suppress("UNUSED_PARAMETER") typeHint: String = "",
  @Suppress("UNUSED_PARAMETER") useSubThreads: Boolean = false,
) {
  ThreadedLoad.request(path)
}

fun ResourceLoader.loadThreadedGetStatusWithProgress(path: String): ThreadedLoadStatus {
  val status = ThreadedLoad.status(path)
  return ThreadedLoadStatus(status, if (status == ThreadedLoad.THREAD_LOAD_LOADED) 1.0 else 0.0)
}

fun ResourceLoader.loadThreadedGetPackedScene(path: String): PackedScene? = ThreadedLoad.take(path)

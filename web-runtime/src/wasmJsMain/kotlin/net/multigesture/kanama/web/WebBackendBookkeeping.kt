@file:OptIn(ExperimentalWasmJsInterop::class, InternalKanamaBackendApi::class)

package net.multigesture.kanama.web

import kotlin.js.ExperimentalWasmJsInterop
import net.multigesture.kanama.backend.GodotColor
import net.multigesture.kanama.backend.GodotHandle
import net.multigesture.kanama.backend.GodotRect2
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.GodotVector3
import net.multigesture.kanama.backend.InternalKanamaBackendApi

/**
 * Hand-written Web-only backend bookkeeping (Task 60a, "Option A").
 *
 * The generated dispatch in `WebCommonGodotBackend.kt` carries the mechanical opcode routing,
 * execution-mode guards, and JS-bridge codecs; the genuinely Web-specific state that a Kotlin/Wasm
 * split-module backend needs — read-your-write property snapshots, browser handle-kind tracking,
 * and free-time cache clearing — lives here and is reached through the hooks in this file. See
 * `docs/contributing/web-internals.md` ("Backend-dispatch codegen") for why this is not folded into
 * the platform-neutral `platform_backend_calls.json`.
 */
private val positionSnapshots = mutableMapOf<Int, GodotVector2>()
private val scaleSnapshots = mutableMapOf<Int, GodotVector2>()
private val modulateSnapshots = mutableMapOf<Int, GodotColor>()
private val textureSnapshots = mutableMapOf<Int, Int>()
private val viewportRectSnapshots = mutableMapOf<Int, GodotRect2>()
private val particlesEmittingSnapshots = mutableMapOf<Int, Boolean>()
private val particlesLifetimeSnapshots = mutableMapOf<Int, Double>()
private val rotationSnapshots = mutableMapOf<Int, Double>()
private val animationNamesSnapshots = mutableMapOf<Int, List<String>>()
private val position3Snapshots = mutableMapOf<Int, GodotVector3>()
private val rotation3Snapshots = mutableMapOf<Int, GodotVector3>()
private val scale3Snapshots = mutableMapOf<Int, GodotVector3>()
private val velocity3Snapshots = mutableMapOf<Int, GodotVector3>()
private val rotationDegrees3Snapshots = mutableMapOf<Int, GodotVector3>()
private val targetPosition3Snapshots = mutableMapOf<Int, GodotVector3>()
private val renderingMethodSnapshots = mutableMapOf<Int, String>()
private val browserHandles = mutableMapOf<Int, WebBrowserHandleKind>()

internal enum class WebBrowserHandleKind {
  RESOURCE,
  NODE,
  OBJECT,
}

/** Which mirrored Vector2 property a snapshot read/write targets. */
internal enum class WebVector2Slot {
  POSITION,
  SCALE,
}

/** Which mirrored Node3D/CharacterBody3D Vector3 property a snapshot read/write targets. */
internal enum class WebVector3Slot {
  POSITION,
  ROTATION,
  SCALE,
  VELOCITY,
  ROTATION_DEGREES,
  TARGET_POSITION,
}

internal fun GodotHandle.webId(): Int = backendToken().toInt()

// ---------------------------------------------------------------------------
// Snapshot reads (SNAPSHOT_READ execution). Return null when no snapshot is
// registered; the generated dispatch turns that into a descriptor-tagged error.
// ---------------------------------------------------------------------------

internal fun webVector2Snapshot(objectId: Int, slot: WebVector2Slot): GodotVector2? =
  when (slot) {
    WebVector2Slot.POSITION -> positionSnapshots[objectId]
    WebVector2Slot.SCALE -> scaleSnapshots[objectId]
  }

internal fun webVector3Snapshot(objectId: Int, slot: WebVector3Slot): GodotVector3? =
  when (slot) {
    WebVector3Slot.POSITION -> position3Snapshots[objectId]
    WebVector3Slot.ROTATION -> rotation3Snapshots[objectId]
    WebVector3Slot.SCALE -> scale3Snapshots[objectId]
    WebVector3Slot.VELOCITY -> velocity3Snapshots[objectId]
    WebVector3Slot.ROTATION_DEGREES -> rotationDegrees3Snapshots[objectId]
    WebVector3Slot.TARGET_POSITION -> targetPosition3Snapshots[objectId]
  }

internal fun webViewportRectSnapshot(objectId: Int): GodotRect2? = viewportRectSnapshots[objectId]

internal fun webRenderingMethodSnapshot(objectId: Int): String? = renderingMethodSnapshots[objectId]

/** RenderingServer.get_current_rendering_method snapshot, seeded by the proxy at ready. */
internal fun loadWebRenderingMethodSnapshot(objectId: Int, value: String) {
  renderingMethodSnapshots[objectId] = value
}

internal fun webModulateSnapshot(objectId: Int): GodotColor? = modulateSnapshots[objectId]

internal fun webEmittingSnapshot(objectId: Int): Boolean? = particlesEmittingSnapshots[objectId]

internal fun webLifetimeSnapshot(objectId: Int): Double? = particlesLifetimeSnapshots[objectId]

internal fun webRotationSnapshot(objectId: Int): Double? = rotationSnapshots[objectId]

internal fun webAnimationNamesSnapshot(objectId: Int): List<String>? =
  animationNamesSnapshots[objectId]

/** Records a SpriteFrames animation-name list (newline-joined by the bridge; empty = no names). */
internal fun loadWebAnimationNames(objectId: Int, joined: String) {
  animationNamesSnapshots[objectId] = if (joined.isEmpty()) emptyList() else joined.split('\n')
}

/** Texture handle id snapshot: null = no snapshot registered, 0 = registered-but-cleared. */
internal fun webTextureSnapshot(objectId: Int): Int? = textureSnapshots[objectId]

// ---------------------------------------------------------------------------
// Snapshot writes (QUEUED_MUTATION read-your-write updates).
// ---------------------------------------------------------------------------

internal fun webWriteVector2Snapshot(objectId: Int, slot: WebVector2Slot, value: GodotVector2) {
  when (slot) {
    WebVector2Slot.POSITION -> positionSnapshots[objectId] = value
    WebVector2Slot.SCALE -> scaleSnapshots[objectId] = value
  }
}

internal fun webWriteModulateSnapshot(objectId: Int, value: GodotColor) {
  modulateSnapshots[objectId] = value
}

internal fun webWriteVector3Snapshot(objectId: Int, slot: WebVector3Slot, value: GodotVector3) {
  when (slot) {
    WebVector3Slot.POSITION -> position3Snapshots[objectId] = value
    WebVector3Slot.ROTATION -> rotation3Snapshots[objectId] = value
    WebVector3Slot.SCALE -> scale3Snapshots[objectId] = value
    WebVector3Slot.VELOCITY -> velocity3Snapshots[objectId] = value
    WebVector3Slot.ROTATION_DEGREES -> rotationDegrees3Snapshots[objectId] = value
    WebVector3Slot.TARGET_POSITION -> targetPosition3Snapshots[objectId] = value
  }
}

internal fun webWriteEmittingSnapshot(objectId: Int, value: Boolean) {
  particlesEmittingSnapshots[objectId] = value
}

internal fun webWriteTextureSnapshot(objectId: Int, textureId: Int) {
  textureSnapshots[objectId] = textureId
}

// ---------------------------------------------------------------------------
// Returned-handle registration hooks.
// ---------------------------------------------------------------------------

internal fun registerReturnedNode(token: Int): GodotHandle? =
  token
    .takeIf { it > 0 }
    ?.let {
      // A handle already tracked under another kind keeps it: a node that was first
      // property-pushed (RESOURCE by convention) may later return from a node lookup
      // (City-Builder's view camera is both an exported property and a requireAs child).
      if (!instances.isLive(it) && !containsWebBrowserHandle(it)) {
        registerWebBrowserHandle(it, WebBrowserHandleKind.NODE)
      }
      GodotHandle.fromBackendToken(it.toLong())
    }

internal fun registerReturnedBrowserObject(token: Int): GodotHandle? =
  token
    .takeIf { it > 0 }
    ?.let {
      registerWebBrowserHandle(it, WebBrowserHandleKind.OBJECT)
      GodotHandle.fromBackendToken(it.toLong())
    }

internal fun existingReturnedObject(receiver: GodotHandle, token: Int): GodotHandle? =
  token
    .takeIf { it > 0 }
    ?.also {
      check(it == receiver.webId()) {
        "Kanama Web fluent call returned handle=$it instead of receiver=${receiver.webId()}"
      }
    }
    ?.let { GodotHandle.fromBackendToken(it.toLong()) }

/** ResourceLoader.load result: register a RESOURCE handle for a successful load. */
internal fun registerLoadedResource(token: Int): GodotHandle? =
  token
    .takeIf { it > 0 }
    ?.let {
      registerWebBrowserHandle(it, WebBrowserHandleKind.RESOURCE)
      GodotHandle.fromBackendToken(it.toLong())
    }

/** ClassDB.instantiate result: register a NODE handle and seed its mirrored snapshots. */
internal fun registerConstructedNode(token: Int, className: String): GodotHandle? =
  token
    .takeIf { it > 0 }
    ?.let {
      registerWebBrowserHandle(it, WebBrowserHandleKind.NODE)
      positionSnapshots[it] = GodotVector2(0.0f, 0.0f)
      scaleSnapshots[it] = GodotVector2(1.0f, 1.0f)
      modulateSnapshots[it] = GodotColor(1.0f, 1.0f, 1.0f, 1.0f)
      if (className == "Sprite2D") textureSnapshots[it] = 0
      GodotHandle.fromBackendToken(it.toLong())
    }

// ---------------------------------------------------------------------------
// Handle-kind requirements and lifecycle.
// ---------------------------------------------------------------------------

internal fun registerWebBrowserHandle(handle: Int, kind: WebBrowserHandleKind) {
  check(handle > 0) { "Kanama Web browser handle must be positive" }
  val previous = browserHandles[handle]
  if (previous == null) browserHandles[handle] = kind
  check(previous == null || previous == kind) {
    "Kanama Web browser handle=$handle already has kind=$previous, requested=$kind"
  }
}

internal fun unregisterWebBrowserHandle(handle: Int, expectedKind: WebBrowserHandleKind) {
  val actual = browserHandles.remove(handle)
  check(actual == expectedKind) {
    "Kanama Web browser handle=$handle has kind=$actual, expected=$expectedKind"
  }
}

internal fun requireWebBrowserHandle(handle: Int, expectedKind: WebBrowserHandleKind) {
  val actual = browserHandles[handle]
  check(actual == expectedKind) {
    "Kanama Web browser handle=$handle has kind=$actual, expected=$expectedKind"
  }
}

internal fun requireWebNodeHandle(handle: Int) {
  if (instances.isLive(handle)) return
  requireWebBrowserHandle(handle, WebBrowserHandleKind.NODE)
}

internal fun containsWebBrowserHandle(handle: Int): Boolean = browserHandles.containsKey(handle)

/**
 * Node.queue_free bookkeeping: release a browser NODE handle and its mirrored snapshots.
 *
 * A live script instance keeps its snapshots: Godot frees the node at end of frame, so its
 * _physics_process/_process can still run (and read mirrored properties) between queue_free and the
 * actual free — kanamaWebFree clears them when the node really exits the tree (squash: a dying
 * Player queue_frees itself from body_entered, then ticks once more).
 */
internal fun onWebQueueFree(objectId: Int) {
  if (!instances.isLive(objectId)) {
    clearWebPositionSnapshot(objectId)
    unregisterWebBrowserHandle(objectId, WebBrowserHandleKind.NODE)
  }
}

internal fun clearWebBrowserHandles() {
  browserHandles.keys.forEach(::clearWebPositionSnapshot)
  browserHandles.clear()
}

internal fun discardWebBrowserHandle(handle: Int): Boolean {
  clearWebPositionSnapshot(handle)
  return browserHandles.remove(handle) != null
}

// ---------------------------------------------------------------------------
// Snapshot loaders invoked from the JS bridge (frame snapshots).
// ---------------------------------------------------------------------------

internal fun loadWebPositionSnapshot(objectId: Int, x: Double, y: Double) {
  check(instances.isLive(objectId) || browserHandles[objectId] == WebBrowserHandleKind.NODE) {
    "Cannot snapshot unknown Kanama Web node handle=$objectId"
  }
  positionSnapshots[objectId] = GodotVector2(x.toFloat(), y.toFloat())
}

internal fun loadWebNode2DSnapshot(
  objectId: Int,
  positionX: Double,
  positionY: Double,
  scaleX: Double,
  scaleY: Double,
  modulateR: Double,
  modulateG: Double,
  modulateB: Double,
  modulateA: Double,
  rotation: Double,
) {
  loadWebPositionSnapshot(objectId, positionX, positionY)
  scaleSnapshots[objectId] = GodotVector2(scaleX.toFloat(), scaleY.toFloat())
  modulateSnapshots[objectId] =
    GodotColor(modulateR.toFloat(), modulateG.toFloat(), modulateB.toFloat(), modulateA.toFloat())
  require(rotation.isFinite()) { "Kanama Web Node2D rotation snapshot must be finite" }
  rotationSnapshots[objectId] = rotation
}

/**
 * Node3D transform frame snapshot pushed from the bridge for scene-graph (non-constructed) nodes.
 */
internal fun loadWebNode3DSnapshot(
  objectId: Int,
  positionX: Double,
  positionY: Double,
  positionZ: Double,
  rotationX: Double,
  rotationY: Double,
  rotationZ: Double,
  scaleX: Double,
  scaleY: Double,
  scaleZ: Double,
) {
  // Any known handle may carry a Node3D snapshot: property pushes register node references
  // under the RESOURCE kind before their transform seed arrives.
  check(instances.isLive(objectId) || browserHandles.containsKey(objectId)) {
    "Cannot snapshot unknown Kanama Web Node3D handle=$objectId"
  }
  position3Snapshots[objectId] =
    GodotVector3(positionX.toFloat(), positionY.toFloat(), positionZ.toFloat())
  rotation3Snapshots[objectId] =
    GodotVector3(rotationX.toFloat(), rotationY.toFloat(), rotationZ.toFloat())
  scale3Snapshots[objectId] = GodotVector3(scaleX.toFloat(), scaleY.toFloat(), scaleZ.toFloat())
  // rotation_degrees is by definition rotation in degrees; derive it from the same refresh so
  // reads work before any write (the camera rig reads it at ready).
  val toDegrees = 180.0 / kotlin.math.PI
  rotationDegrees3Snapshots[objectId] =
    GodotVector3(
      (rotationX * toDegrees).toFloat(),
      (rotationY * toDegrees).toFloat(),
      (rotationZ * toDegrees).toFloat(),
    )
}

/** CharacterBody3D velocity refresh: the proxy pushes the post-slide velocity each physics tick. */
internal fun loadWebVelocitySnapshot(objectId: Int, x: Double, y: Double, z: Double) {
  velocity3Snapshots[objectId] = GodotVector3(x.toFloat(), y.toFloat(), z.toFloat())
}

internal fun loadWebViewportRectSnapshot(
  objectId: Int,
  x: Double,
  y: Double,
  width: Double,
  height: Double,
) {
  check(instances.isLive(objectId) || browserHandles[objectId] == WebBrowserHandleKind.NODE) {
    "Cannot snapshot unknown Kanama Web node handle=$objectId"
  }
  viewportRectSnapshots[objectId] =
    GodotRect2(
      GodotVector2(x.toFloat(), y.toFloat()),
      GodotVector2(width.toFloat(), height.toFloat()),
    )
}

internal fun loadWebParticlesSnapshot(objectId: Int, emitting: Boolean, lifetime: Double) {
  check(instances.isLive(objectId) || browserHandles[objectId] == WebBrowserHandleKind.NODE) {
    "Cannot snapshot unknown Kanama Web GPUParticles2D handle=$objectId"
  }
  require(lifetime.isFinite() && lifetime >= 0.0) {
    "Kanama Web GPUParticles2D lifetime must be finite and non-negative"
  }
  particlesEmittingSnapshots[objectId] = emitting
  particlesLifetimeSnapshots[objectId] = lifetime
}

internal fun webParticlesSnapshotCount(): Int = particlesLifetimeSnapshots.size

internal fun clearWebPositionSnapshot(objectId: Int) {
  positionSnapshots.remove(objectId)
  scaleSnapshots.remove(objectId)
  modulateSnapshots.remove(objectId)
  textureSnapshots.remove(objectId)
  viewportRectSnapshots.remove(objectId)
  particlesEmittingSnapshots.remove(objectId)
  particlesLifetimeSnapshots.remove(objectId)
  rotationSnapshots.remove(objectId)
  animationNamesSnapshots.remove(objectId)
  position3Snapshots.remove(objectId)
  rotation3Snapshots.remove(objectId)
  scale3Snapshots.remove(objectId)
  velocity3Snapshots.remove(objectId)
  rotationDegrees3Snapshots.remove(objectId)
  targetPosition3Snapshots.remove(objectId)
  renderingMethodSnapshots.remove(objectId)
}

// ---------------------------------------------------------------------------
// Pure-Kotlin RNG backing the @GlobalScope utility opcodes (no bridge crossing).
// ---------------------------------------------------------------------------

internal object WebRandom {
  private var state = 0x9e3779b97f4a7c15UL

  fun randomize() {
    var seed = webRandomSeed().toLong().toULong() xor 0x9e3779b97f4a7c15UL
    seed = (seed xor (seed shr 30)) * 0xbf58476d1ce4e5b9UL
    seed = (seed xor (seed shr 27)) * 0x94d049bb133111ebUL
    state = (seed xor (seed shr 31)).takeUnless { it == 0UL } ?: 0x2545f4914f6cdd1dUL
  }

  fun randi(): Long {
    var next = state
    next = next xor (next shl 13)
    next = next xor (next shr 7)
    next = next xor (next shl 17)
    state = next
    return (next and 0xffff_ffffUL).toLong()
  }

  fun randf(): Double = randi().toDouble() / 4_294_967_295.0
}

private fun webRandomSeed(): Double =
  js(
    "((globalThis.crypto && globalThis.crypto.getRandomValues) ? globalThis.crypto.getRandomValues(new Uint32Array(1))[0] : Date.now()) + performance.now()"
  )

@file:OptIn(ExperimentalWasmJsInterop::class, InternalKanamaBackendApi::class)

package net.multigesture.kanama.web

import kotlin.js.ExperimentalWasmJsInterop
import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.GodotBackendSpi
import net.multigesture.kanama.backend.GodotCallDescriptor
import net.multigesture.kanama.backend.GodotCallSite
import net.multigesture.kanama.backend.GodotColor
import net.multigesture.kanama.backend.GodotExecutionMode
import net.multigesture.kanama.backend.GodotHandle
import net.multigesture.kanama.backend.GodotRect2
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.InternalKanamaBackendApi

private val positionSnapshots = mutableMapOf<Int, GodotVector2>()
private val viewportRectSnapshots = mutableMapOf<Int, GodotRect2>()
private val browserHandles = mutableMapOf<Int, WebBrowserHandleKind>()

internal enum class WebBrowserHandleKind {
  RESOURCE,
  SPRITE2D,
}

/** Kotlin/Wasm implementation: snapshot reads, queued Vector2 mutations, explicit sync barrier. */
internal object WebCommonGodotBackend : GodotBackendSpi {
  override fun requireLive(handle: GodotHandle) {
    val token = handle.webId()
    if (!instances.isLive(token)) {
      check(browserHandles.containsKey(token)) { "Stale Kanama Web browser handle=$token" }
    }
  }

  override fun resolve(descriptor: GodotCallDescriptor): GodotCallSite =
    GodotCallSite.fromBackendToken(descriptor.opcode.toLong())

  override fun invokeBoolRetInt(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Boolean,
  ): Int {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    commands.flush() // explicit ordering barrier for mutations issued before this result
    return immediateWebChildCount(receiver.webId(), value)
  }

  override fun invokeNoArgsRetVector2(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): GodotVector2 {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.SNAPSHOT_READ)
    return positionSnapshots[receiver.webId()]
      ?: error("Missing Web frame snapshot for object handle=${receiver.webId()}")
  }

  override fun invokeVector2Arg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotVector2,
  ) {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.QUEUED_MUTATION)
    val objectId = receiver.webId()
    commands.appendPositionMutation(objectId, value.x, value.y)
    positionSnapshots[objectId] = value // read-your-write overlay for the current phase
  }

  override fun invokeNoArgsRetRect2(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): GodotRect2 {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.SNAPSHOT_READ)
    return viewportRectSnapshots[receiver.webId()]
      ?: error("Missing Web viewport snapshot for object handle=${receiver.webId()}")
  }

  override fun invokeNoArgsVoid(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ) {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.QUEUED_MUTATION)
    val objectId = receiver.webId()
    commands.appendNoArgsMutation(descriptor.opcode, objectId)
    if (descriptor.opcode == 15) {
      positionSnapshots.remove(objectId)
      unregisterWebBrowserHandle(objectId, WebBrowserHandleKind.SPRITE2D)
    }
  }

  override fun invokeTexture2DVector2ColorArgs(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    texture: GodotHandle,
    position: GodotVector2,
    modulate: GodotColor,
  ) {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.QUEUED_MUTATION)
    requireWebBrowserHandle(texture.webId(), WebBrowserHandleKind.RESOURCE)
    drawCommands.appendDrawTexture(
      descriptor.opcode,
      receiver.webId(),
      texture.webId(),
      position.x,
      position.y,
      modulate.r,
      modulate.g,
      modulate.b,
      modulate.a,
    )
  }

  override fun invokeStringStringLongRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    first: String,
    second: String,
    value: Long,
  ): GodotHandle? {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    require(value in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())
    commands.flush()
    val token = immediateWebResourceLoad(first, second, value.toInt())
    return token
      .takeIf { it > 0 }
      ?.let {
        registerWebBrowserHandle(it, WebBrowserHandleKind.RESOURCE)
        GodotHandle.fromBackendToken(it.toLong())
      }
  }

  override fun invokeUtilityNoArgsVoid(descriptor: GodotCallDescriptor, callSite: GodotCallSite) {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    WebRandom.randomize()
  }

  override fun invokeUtilityNoArgsRetLong(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
  ): Long {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    return WebRandom.randi()
  }

  override fun invokeUtilityNoArgsRetDouble(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
  ): Double {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    return WebRandom.randf()
  }

  override fun invokeStringNameIntRetInt(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    name: String,
    value: Int,
  ): Int {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    commands.flush()
    return immediateWebEmitSignal(receiver.webId(), name, value)
  }

  override fun invokeStringNameRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    value: String,
  ): GodotHandle? {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    commands.flush()
    val token = immediateWebConstructObject(value)
    return token
      .takeIf { it > 0 }
      ?.let {
        registerWebBrowserHandle(it, WebBrowserHandleKind.SPRITE2D)
        positionSnapshots[it] = GodotVector2(0.0f, 0.0f)
        GodotHandle.fromBackendToken(it.toLong())
      }
  }

  override fun invokeObjectBoolLongArgs(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    objectValue: GodotHandle,
    boolValue: Boolean,
    longValue: Long,
  ) {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.QUEUED_MUTATION)
    requireWebBrowserHandle(objectValue.webId(), WebBrowserHandleKind.SPRITE2D)
    commands.appendObjectBoolLongArgs(
      descriptor.opcode,
      receiver.webId(),
      objectValue.webId(),
      boolValue,
      longValue,
    )
  }

  override fun invokeObjectArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotHandle?,
  ) {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.QUEUED_MUTATION)
    when (descriptor.opcode) {
      14 -> requireWebBrowserHandle(checkNotNull(value).webId(), WebBrowserHandleKind.SPRITE2D)
      16 -> value?.let { requireWebBrowserHandle(it.webId(), WebBrowserHandleKind.RESOURCE) }
      else -> error("Unsupported Web object-argument opcode=${descriptor.opcode}")
    }
    commands.appendObjectArg(descriptor.opcode, receiver.webId(), value?.webId() ?: 0)
  }

  private fun requireOpcode(descriptor: GodotCallDescriptor, callSite: GodotCallSite) {
    require(callSite.backendToken() == descriptor.opcode.toLong()) {
      "Web Godot call-site opcode does not match ${descriptor.className}.${descriptor.methodName}"
    }
  }
}

internal fun installWebCommonGodotBackend() {
  GodotBackendCalls.install(WebCommonGodotBackend)
}

internal fun loadWebPositionSnapshot(objectId: Int, x: Double, y: Double) {
  instances.require(objectId)
  positionSnapshots[objectId] = GodotVector2(x.toFloat(), y.toFloat())
}

internal fun loadWebViewportRectSnapshot(
  objectId: Int,
  x: Double,
  y: Double,
  width: Double,
  height: Double,
) {
  instances.require(objectId)
  viewportRectSnapshots[objectId] =
    GodotRect2(
      GodotVector2(x.toFloat(), y.toFloat()),
      GodotVector2(width.toFloat(), height.toFloat()),
    )
}

internal fun clearWebPositionSnapshot(objectId: Int) {
  positionSnapshots.remove(objectId)
  viewportRectSnapshots.remove(objectId)
}

private fun GodotHandle.webId(): Int = backendToken().toInt()

private fun immediateWebChildCount(objectId: Int, includeInternal: Boolean): Int =
  js("globalThis.KanamaWebBridge.immediateChildCount(objectId, includeInternal)")

private fun immediateWebResourceLoad(path: String, typeHint: String, cacheMode: Int): Int =
  js("globalThis.KanamaWebBridge.immediateResourceLoad(path, typeHint, cacheMode)")

private fun immediateWebEmitSignal(objectId: Int, name: String, value: Int): Int =
  js("globalThis.KanamaWebBridge.immediateEmitSignal(objectId, name, value)")

private fun immediateWebConstructObject(className: String): Int =
  js("globalThis.KanamaWebBridge.immediateConstructObject(className)")

internal fun registerWebBrowserHandle(handle: Int, kind: WebBrowserHandleKind) {
  check(handle > 0) { "Kanama Web browser handle must be positive" }
  check(browserHandles.put(handle, kind) == null) { "Duplicate Kanama Web browser handle=$handle" }
}

internal fun unregisterWebBrowserHandle(handle: Int, expectedKind: WebBrowserHandleKind) {
  val actual = browserHandles.remove(handle)
  check(actual == expectedKind) {
    "Kanama Web browser handle=$handle has kind=$actual, expected=$expectedKind"
  }
}

internal fun clearWebBrowserHandles() {
  browserHandles.keys.forEach(positionSnapshots::remove)
  browserHandles.clear()
}

private fun requireWebBrowserHandle(handle: Int, expectedKind: WebBrowserHandleKind) {
  val actual = browserHandles[handle]
  check(actual == expectedKind) {
    "Kanama Web browser handle=$handle has kind=$actual, expected=$expectedKind"
  }
}

private object WebRandom {
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

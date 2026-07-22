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
import net.multigesture.kanama.backend.GodotVector2i
import net.multigesture.kanama.backend.InternalKanamaBackendApi

private val positionSnapshots = mutableMapOf<Int, GodotVector2>()
private val viewportRectSnapshots = mutableMapOf<Int, GodotRect2>()
private val browserHandles = mutableMapOf<Int, WebBrowserHandleKind>()

internal enum class WebBrowserHandleKind {
  RESOURCE,
  NODE,
  OBJECT,
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
    return when (descriptor.executionMode) {
      GodotExecutionMode.SNAPSHOT_READ ->
        positionSnapshots[receiver.webId()]
          ?: error("Missing Web frame snapshot for object handle=${receiver.webId()}")
      GodotExecutionMode.IMMEDIATE_RESULT -> {
        commands.flush()
        GodotVector2(
          immediateWebNoArgsVector2X(descriptor.opcode, receiver.webId()).toFloat(),
          immediateWebNoArgsVector2Y().toFloat(),
        )
      }
      GodotExecutionMode.QUEUED_MUTATION ->
        error("Vector2 return cannot use queued execution for opcode=${descriptor.opcode}")
    }
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
      if (!instances.isLive(objectId)) {
        unregisterWebBrowserHandle(objectId, WebBrowserHandleKind.NODE)
      }
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
        registerWebBrowserHandle(it, WebBrowserHandleKind.NODE)
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
    requireWebNodeHandle(objectValue.webId())
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
      14 -> requireWebNodeHandle(checkNotNull(value).webId())
      16 -> value?.let { requireWebBrowserHandle(it.webId(), WebBrowserHandleKind.RESOURCE) }
      else -> error("Unsupported Web object-argument opcode=${descriptor.opcode}")
    }
    commands.appendObjectArg(descriptor.opcode, receiver.webId(), value?.webId() ?: 0)
  }

  override fun invokeNodePathRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    path: String,
  ): GodotHandle? {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    commands.flush()
    return registerReturnedNode(immediateWebNodeLookup(receiver.webId(), path))
  }

  override fun invokeLongRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Long,
  ): GodotHandle? {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    require(value in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())
    commands.flush()
    return registerReturnedNode(immediateWebPackedSceneInstantiate(receiver.webId(), value.toInt()))
  }

  override fun invokeNoArgsRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): GodotHandle? {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    commands.flush()
    return registerReturnedNode(immediateWebNoArgsObject(descriptor.opcode, receiver.webId()))
  }

  override fun invokeObjectLongVector2Args(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    objectValue: GodotHandle?,
    longValue: Long,
    vectorValue: GodotVector2,
  ) {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    require(longValue in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())
    objectValue?.let { requireWebBrowserHandle(it.webId(), WebBrowserHandleKind.RESOURCE) }
    commands.flush()
    immediateWebSetCustomMouseCursor(
      requireActiveWebScriptHandle(),
      objectValue?.webId() ?: 0,
      longValue.toInt(),
      vectorValue.x.toDouble(),
      vectorValue.y.toDouble(),
    )
  }

  override fun invokeStringNameCallableLongRetLong(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    signal: String,
    target: GodotHandle,
    method: String,
    flags: Long,
  ): Long {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    require(flags in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())
    commands.flush()
    return immediateWebConnect(receiver.webId(), signal, target.webId(), method, flags.toInt())
      .toLong()
  }

  override fun invokeStringNameRetBool(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: String,
  ): Boolean {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    commands.flush()
    return immediateWebObjectQuery(descriptor.opcode, receiver.webId(), value) != 0
  }

  override fun invokeNoArgsRetBool(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): Boolean {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    commands.flush()
    return immediateWebObjectQuery(descriptor.opcode, receiver.webId(), "") != 0
  }

  override fun invokeNoArgsRetLong(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): Long {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    commands.flush()
    return immediateWebObjectQuery(descriptor.opcode, receiver.webId(), "").toLong()
  }

  override fun invokeStringNameVector2iRetInt(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    name: String,
    value: GodotVector2i,
  ): Int {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    commands.flush()
    return immediateWebEmitSignalVector2i(receiver.webId(), name, value.x, value.y)
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
  check(instances.isLive(objectId) || browserHandles[objectId] == WebBrowserHandleKind.NODE) {
    "Cannot snapshot unknown Kanama Web node handle=$objectId"
  }
  positionSnapshots[objectId] = GodotVector2(x.toFloat(), y.toFloat())
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

private fun immediateWebNodeLookup(objectId: Int, path: String): Int =
  js("globalThis.KanamaWebBridge.immediateNodeLookup(objectId, path)")

private fun immediateWebPackedSceneInstantiate(resourceId: Int, editState: Int): Int =
  js("globalThis.KanamaWebBridge.immediatePackedSceneInstantiate(resourceId, editState)")

private fun immediateWebNoArgsObject(opcode: Int, objectId: Int): Int =
  js("globalThis.KanamaWebBridge.immediateNoArgsObject(opcode, objectId)")

private fun immediateWebSetCustomMouseCursor(
  ownerId: Int,
  resourceId: Int,
  shape: Int,
  hotspotX: Double,
  hotspotY: Double,
): Int =
  js(
    "globalThis.KanamaWebBridge.immediateSetCustomMouseCursor(ownerId, resourceId, shape, hotspotX, hotspotY)"
  )

private fun immediateWebConnect(
  objectId: Int,
  signal: String,
  targetId: Int,
  method: String,
  flags: Int,
): Int =
  js("globalThis.KanamaWebBridge.immediateConnect(objectId, signal, targetId, method, flags)")

private fun immediateWebObjectQuery(opcode: Int, objectId: Int, value: String): Int =
  js("globalThis.KanamaWebBridge.immediateObjectQuery(opcode, objectId, value)")

private fun immediateWebNoArgsVector2X(opcode: Int, objectId: Int): Double =
  js("globalThis.KanamaWebBridge.immediateNoArgsVector2X(opcode, objectId)")

private fun immediateWebNoArgsVector2Y(): Double =
  js("globalThis.KanamaWebBridge.immediateNoArgsVector2Y()")

private fun immediateWebEmitSignalVector2i(objectId: Int, name: String, x: Int, y: Int): Int =
  js("globalThis.KanamaWebBridge.immediateEmitSignalVector2i(objectId, name, x, y)")

private fun registerReturnedNode(token: Int): GodotHandle? =
  token
    .takeIf { it > 0 }
    ?.let {
      if (!instances.isLive(it)) registerWebBrowserHandle(it, WebBrowserHandleKind.NODE)
      GodotHandle.fromBackendToken(it.toLong())
    }

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

internal fun clearWebBrowserHandles() {
  browserHandles.keys.forEach(positionSnapshots::remove)
  browserHandles.clear()
}

internal fun discardWebBrowserHandle(handle: Int): Boolean {
  positionSnapshots.remove(handle)
  viewportRectSnapshots.remove(handle)
  return browserHandles.remove(handle) != null
}

private fun requireWebBrowserHandle(handle: Int, expectedKind: WebBrowserHandleKind) {
  val actual = browserHandles[handle]
  check(actual == expectedKind) {
    "Kanama Web browser handle=$handle has kind=$actual, expected=$expectedKind"
  }
}

private fun requireWebNodeHandle(handle: Int) {
  if (instances.isLive(handle)) return
  requireWebBrowserHandle(handle, WebBrowserHandleKind.NODE)
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

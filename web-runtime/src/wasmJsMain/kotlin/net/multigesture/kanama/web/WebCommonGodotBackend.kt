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
private val scaleSnapshots = mutableMapOf<Int, GodotVector2>()
private val modulateSnapshots = mutableMapOf<Int, GodotColor>()
private val textureSnapshots = mutableMapOf<Int, Int>()
private val viewportRectSnapshots = mutableMapOf<Int, GodotRect2>()
private val particlesEmittingSnapshots = mutableMapOf<Int, Boolean>()
private val particlesLifetimeSnapshots = mutableMapOf<Int, Double>()
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

  override fun invokeBoolRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Boolean,
  ): GodotHandle? {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    commands.flush()
    return existingReturnedObject(
      receiver,
      immediateWebTweenBoolRetObject(descriptor.opcode, receiver.webId(), value),
    )
  }

  override fun invokeBoolArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Boolean,
  ) {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.QUEUED_MUTATION)
    require(descriptor.opcode == 43)
    val objectId = receiver.webId()
    commands.appendBoolMutation(descriptor.opcode, objectId, value)
    particlesEmittingSnapshots[objectId] = value
  }

  override fun invokeDoubleArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Double,
  ) {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.QUEUED_MUTATION)
    require(descriptor.opcode in 48..50)
    require(value.isFinite()) {
      "Kanama Web ${descriptor.className}.${descriptor.methodName} requires a finite Double"
    }
    commands.appendDoubleMutation(descriptor.opcode, receiver.webId(), value)
  }

  override fun invokeLongArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: Long,
  ) {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.QUEUED_MUTATION)
    require(descriptor.opcode == 52)
    require(value in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong()) {
      "Kanama Web SceneTree.quit exit code must fit Godot's int32 ABI"
    }
    commands.appendLongMutation(descriptor.opcode, receiver.webId(), value)
  }

  override fun invokeNoArgsRetVector2(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): GodotVector2 {
    requireOpcode(descriptor, callSite)
    return when (descriptor.executionMode) {
      GodotExecutionMode.SNAPSHOT_READ ->
        when (descriptor.opcode) {
          2 -> positionSnapshots[receiver.webId()]
          29 -> scaleSnapshots[receiver.webId()]
          else -> null
        }
          ?: error(
            "Missing Web ${descriptor.className}.${descriptor.methodName} snapshot for " +
              "object handle=${receiver.webId()}"
          )
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
    commands.appendVector2Mutation(descriptor.opcode, objectId, value.x, value.y)
    when (descriptor.opcode) {
      3 -> positionSnapshots[objectId] = value
      30 -> scaleSnapshots[objectId] = value
      else -> error("Unsupported Web Vector2 mutation opcode=${descriptor.opcode}")
    }
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
    val objectId = receiver.webId()
    when (descriptor.executionMode) {
      GodotExecutionMode.QUEUED_MUTATION -> {
        commands.appendNoArgsMutation(descriptor.opcode, objectId)
        if (descriptor.opcode == 15) {
          clearWebPositionSnapshot(objectId)
          if (!instances.isLive(objectId)) {
            unregisterWebBrowserHandle(objectId, WebBrowserHandleKind.NODE)
          }
        }
      }
      GodotExecutionMode.IMMEDIATE_RESULT -> {
        require(descriptor.opcode == 37)
        commands.flush()
        check(immediateWebTweenNoArgs(descriptor.opcode, objectId) == 1) {
          "Kanama Web Tween.kill failed for handle=$objectId"
        }
      }
      GodotExecutionMode.SNAPSHOT_READ ->
        error("Void call cannot use snapshot execution for opcode=${descriptor.opcode}")
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

  override fun invokeStringNameRetInt(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: String,
  ): Int {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    commands.flush()
    return immediateWebEmitSignalNoArgs(receiver.webId(), value)
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
        scaleSnapshots[it] = GodotVector2(1.0f, 1.0f)
        modulateSnapshots[it] = GodotColor(1.0f, 1.0f, 1.0f, 1.0f)
        if (value == "Sprite2D") textureSnapshots[it] = 0
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
    when (descriptor.opcode) {
      14 -> {
        require(descriptor.executionMode == GodotExecutionMode.QUEUED_MUTATION)
        requireWebNodeHandle(checkNotNull(value).webId())
      }
      16 -> {
        require(descriptor.executionMode == GodotExecutionMode.QUEUED_MUTATION)
        value?.let { requireWebBrowserHandle(it.webId(), WebBrowserHandleKind.RESOURCE) }
        textureSnapshots[receiver.webId()] = value?.webId() ?: 0
      }
      46 -> {
        require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
        value?.let { requireWebBrowserHandle(it.webId(), WebBrowserHandleKind.RESOURCE) }
      }
      else -> error("Unsupported Web object-argument opcode=${descriptor.opcode}")
    }
    commands.appendObjectArg(descriptor.opcode, receiver.webId(), value?.webId() ?: 0)
    if (descriptor.opcode == 46) commands.flush()
  }

  override fun invokeStringNameArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: String,
  ) {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.QUEUED_MUTATION)
    require(descriptor.opcode == 47)
    commands.appendStringNameMutation(descriptor.opcode, receiver.webId(), value)
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
    return when (descriptor.opcode) {
      18 ->
        registerReturnedNode(immediateWebPackedSceneInstantiate(receiver.webId(), value.toInt()))
      41,
      42 ->
        existingReturnedObject(
          receiver,
          immediateWebTweenLongRetObject(descriptor.opcode, receiver.webId(), value.toInt()),
        )
      else -> error("Unsupported Web Long-return-handle opcode=${descriptor.opcode}")
    }
  }

  override fun invokeNoArgsRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): GodotHandle? {
    requireOpcode(descriptor, callSite)
    return when (descriptor.executionMode) {
      GodotExecutionMode.IMMEDIATE_RESULT -> {
        commands.flush()
        val token = immediateWebNoArgsObject(descriptor.opcode, receiver.webId())
        when (descriptor.opcode) {
          19 -> registerReturnedNode(token)
          36 -> registerReturnedBrowserObject(token)
          51 -> registerReturnedBrowserObject(token)
          else -> error("Unsupported Web no-args-object opcode=${descriptor.opcode}")
        }
      }
      GodotExecutionMode.SNAPSHOT_READ -> {
        require(descriptor.opcode == 33)
        val objectId = receiver.webId()
        val textureId =
          textureSnapshots[objectId]
            ?: error("Missing Web Sprite2D.get_texture snapshot for object handle=$objectId")
        textureId.takeIf { it > 0 }?.let { GodotHandle.fromBackendToken(it.toLong()) }
      }
      GodotExecutionMode.QUEUED_MUTATION ->
        error("Handle return cannot use queued execution for opcode=${descriptor.opcode}")
    }
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

  override fun invokeStringNameBoundCallableLongRetLong(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    signal: String,
    target: GodotHandle,
    method: String,
    boundValue: Long,
    flags: Long,
  ): Long {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    require(boundValue in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())
    require(flags in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())
    commands.flush()
    return immediateWebConnectBound(
        receiver.webId(),
        signal,
        target.webId(),
        method,
        boundValue.toInt(),
        flags.toInt(),
      )
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
    return when (descriptor.executionMode) {
      GodotExecutionMode.SNAPSHOT_READ -> {
        require(descriptor.opcode == 44)
        particlesEmittingSnapshots[receiver.webId()]
          ?: error(
            "Missing Web GPUParticles2D.is_emitting snapshot for object handle=${receiver.webId()}"
          )
      }
      GodotExecutionMode.IMMEDIATE_RESULT -> {
        commands.flush()
        immediateWebObjectQuery(descriptor.opcode, receiver.webId(), "") != 0
      }
      GodotExecutionMode.QUEUED_MUTATION ->
        error("Boolean return cannot use queued execution for opcode=${descriptor.opcode}")
    }
  }

  override fun invokeNoArgsRetDouble(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): Double {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.SNAPSHOT_READ)
    require(descriptor.opcode == 45)
    return particlesLifetimeSnapshots[receiver.webId()]
      ?: error(
        "Missing Web GPUParticles2D.get_lifetime snapshot for object handle=${receiver.webId()}"
      )
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

  override fun invokeNoArgsRetColor(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
  ): GodotColor {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.SNAPSHOT_READ)
    return modulateSnapshots[receiver.webId()]
      ?: error("Missing Web CanvasItem.get_modulate snapshot for object handle=${receiver.webId()}")
  }

  override fun invokeColorArg(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    value: GodotColor,
  ) {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.QUEUED_MUTATION)
    commands.appendColorMutation(
      descriptor.opcode,
      receiver.webId(),
      value.r,
      value.g,
      value.b,
      value.a,
    )
    modulateSnapshots[receiver.webId()] = value
  }

  override fun invokeObjectNodePathVector2DoubleRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    target: GodotHandle,
    property: String,
    finalValue: GodotVector2,
    duration: Double,
  ): GodotHandle? {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    require(duration.isFinite() && duration >= 0.0)
    commands.flush()
    return registerReturnedBrowserObject(
      immediateWebTweenPropertyVector2(
        descriptor.opcode,
        receiver.webId(),
        target.webId(),
        property,
        finalValue.x.toDouble(),
        finalValue.y.toDouble(),
        duration,
      )
    )
  }

  override fun invokeObjectNodePathColorDoubleRetHandle(
    descriptor: GodotCallDescriptor,
    callSite: GodotCallSite,
    receiver: GodotHandle,
    target: GodotHandle,
    property: String,
    finalValue: GodotColor,
    duration: Double,
  ): GodotHandle? {
    requireOpcode(descriptor, callSite)
    require(descriptor.executionMode == GodotExecutionMode.IMMEDIATE_RESULT)
    require(duration.isFinite() && duration >= 0.0)
    commands.flush()
    return registerReturnedBrowserObject(
      immediateWebTweenPropertyColor(
        descriptor.opcode,
        receiver.webId(),
        target.webId(),
        property,
        finalValue.r.toDouble(),
        finalValue.g.toDouble(),
        finalValue.b.toDouble(),
        finalValue.a.toDouble(),
        duration,
      )
    )
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
) {
  loadWebPositionSnapshot(objectId, positionX, positionY)
  scaleSnapshots[objectId] = GodotVector2(scaleX.toFloat(), scaleY.toFloat())
  modulateSnapshots[objectId] =
    GodotColor(modulateR.toFloat(), modulateG.toFloat(), modulateB.toFloat(), modulateA.toFloat())
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
}

private fun GodotHandle.webId(): Int = backendToken().toInt()

private fun immediateWebChildCount(objectId: Int, includeInternal: Boolean): Int =
  js("globalThis.KanamaWebBridge.immediateChildCount(objectId, includeInternal)")

private fun immediateWebResourceLoad(path: String, typeHint: String, cacheMode: Int): Int =
  js("globalThis.KanamaWebBridge.immediateResourceLoad(path, typeHint, cacheMode)")

private fun immediateWebEmitSignal(objectId: Int, name: String, value: Int): Int =
  js("globalThis.KanamaWebBridge.immediateEmitSignal(objectId, name, value)")

private fun immediateWebEmitSignalNoArgs(objectId: Int, name: String): Int =
  js("globalThis.KanamaWebBridge.immediateEmitSignalNoArgs(objectId, name)")

private fun immediateWebConstructObject(className: String): Int =
  js("globalThis.KanamaWebBridge.immediateConstructObject(className)")

private fun immediateWebNodeLookup(objectId: Int, path: String): Int =
  js("globalThis.KanamaWebBridge.immediateNodeLookup(objectId, path)")

private fun immediateWebPackedSceneInstantiate(resourceId: Int, editState: Int): Int =
  js("globalThis.KanamaWebBridge.immediatePackedSceneInstantiate(resourceId, editState)")

private fun immediateWebNoArgsObject(opcode: Int, objectId: Int): Int =
  js("globalThis.KanamaWebBridge.immediateNoArgsObject(opcode, objectId)")

private fun immediateWebTweenNoArgs(opcode: Int, objectId: Int): Int =
  js("globalThis.KanamaWebBridge.immediateTweenNoArgs(opcode, objectId)")

private fun immediateWebTweenBoolRetObject(opcode: Int, objectId: Int, value: Boolean): Int =
  js("globalThis.KanamaWebBridge.immediateTweenBoolRetObject(opcode, objectId, value)")

private fun immediateWebTweenLongRetObject(opcode: Int, objectId: Int, value: Int): Int =
  js("globalThis.KanamaWebBridge.immediateTweenLongRetObject(opcode, objectId, value)")

private fun immediateWebTweenPropertyVector2(
  opcode: Int,
  tweenId: Int,
  targetId: Int,
  property: String,
  x: Double,
  y: Double,
  duration: Double,
): Int =
  js(
    "globalThis.KanamaWebBridge.immediateTweenPropertyVector2(opcode, tweenId, targetId, property, x, y, duration)"
  )

private fun immediateWebTweenPropertyColor(
  opcode: Int,
  tweenId: Int,
  targetId: Int,
  property: String,
  r: Double,
  g: Double,
  b: Double,
  a: Double,
  duration: Double,
): Int =
  js(
    "globalThis.KanamaWebBridge.immediateTweenPropertyColor(opcode, tweenId, targetId, property, r, g, b, a, duration)"
  )

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

private fun immediateWebConnectBound(
  objectId: Int,
  signal: String,
  targetId: Int,
  method: String,
  boundValue: Int,
  flags: Int,
): Int =
  js(
    "globalThis.KanamaWebBridge.immediateConnectBound(objectId, signal, targetId, method, boundValue, flags)"
  )

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

private fun registerReturnedBrowserObject(token: Int): GodotHandle? =
  token
    .takeIf { it > 0 }
    ?.let {
      registerWebBrowserHandle(it, WebBrowserHandleKind.OBJECT)
      GodotHandle.fromBackendToken(it.toLong())
    }

private fun existingReturnedObject(receiver: GodotHandle, token: Int): GodotHandle? =
  token
    .takeIf { it > 0 }
    ?.also {
      check(it == receiver.webId()) {
        "Kanama Web fluent call returned handle=$it instead of receiver=${receiver.webId()}"
      }
    }
    ?.let { GodotHandle.fromBackendToken(it.toLong()) }

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
  browserHandles.keys.forEach(::clearWebPositionSnapshot)
  browserHandles.clear()
}

internal fun discardWebBrowserHandle(handle: Int): Boolean {
  clearWebPositionSnapshot(handle)
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

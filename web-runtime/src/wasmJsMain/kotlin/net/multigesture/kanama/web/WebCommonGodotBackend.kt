@file:OptIn(ExperimentalWasmJsInterop::class, InternalKanamaBackendApi::class)

package net.multigesture.kanama.web

import kotlin.js.ExperimentalWasmJsInterop
import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.GodotBackendSpi
import net.multigesture.kanama.backend.GodotCallDescriptor
import net.multigesture.kanama.backend.GodotCallSite
import net.multigesture.kanama.backend.GodotExecutionMode
import net.multigesture.kanama.backend.GodotHandle
import net.multigesture.kanama.backend.GodotRect2
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.InternalKanamaBackendApi

private val positionSnapshots = mutableMapOf<Int, GodotVector2>()
private val viewportRectSnapshots = mutableMapOf<Int, GodotRect2>()

/** Kotlin/Wasm implementation: snapshot reads, queued Vector2 mutations, explicit sync barrier. */
internal object WebCommonGodotBackend : GodotBackendSpi {
  override fun requireLive(handle: GodotHandle) {
    instances.require(handle.webId())
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
    commands.appendNoArgsMutation(descriptor.opcode, receiver.webId())
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

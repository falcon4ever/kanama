@file:OptIn(ExperimentalJsExport::class, ExperimentalWasmJsInterop::class)

package net.multigesture.kanama.web

import kotlin.js.ExperimentalJsExport
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsExport
import net.multigesture.kanama.backend.GodotHandle
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.backend.Node2DBackendContractProbe
import net.multigesture.kanama.web.generated.KanamaWebProjectRegistry

internal val instances = WebInstanceRegistry(KanamaWebProjectRegistry::create)
internal val commands = WebCommandBuffer(WebCommandBuffer.BENCHMARK_COMMAND_CAPACITY)
private var frameSequence = 0

@JsExport fun kanamaWebProtocolVersion(): Int = KanamaWebProjectRegistry.PROTOCOL_VERSION

@JsExport fun kanamaWebRoundTrip(value: Int): Int = value

@JsExport
fun kanamaWebCreate(scriptId: Int): Int {
  return instances.create(scriptId)
}

@JsExport fun kanamaWebIsLive(objectHandle: Int): Int = if (instances.isLive(objectHandle)) 1 else 0

@JsExport
fun kanamaWebReady(objectId: Int): Int {
  val record = instances.require(objectId)
  KanamaWebProjectRegistry.ready(record.scriptId, record.script)
  return 1
}

@JsExport
fun kanamaWebProcess(objectId: Int, delta: Double): Int {
  val record = instances.require(objectId)
  KanamaWebProjectRegistry.process(record.scriptId, record.script, delta)
  frameSequence += 1
  commands.clear()
  commands.appendScalarMutation(objectId, frameSequence)
  return commands.flush()
}

@JsExport
fun kanamaWebEmptyFrame(objectId: Int, delta: Double): Int {
  instances.require(objectId)
  return if (delta >= 0.0) 1 else 0
}

@JsExport
fun kanamaWebGetStringProperty(objectId: Int, propertyId: Int): String {
  val record = instances.require(objectId)
  return KanamaWebProjectRegistry.getStringProperty(record.scriptId, propertyId, record.script)
}

@JsExport
fun kanamaWebSetStringProperty(objectId: Int, propertyId: Int, value: String): Int {
  val record = instances.require(objectId)
  KanamaWebProjectRegistry.setStringProperty(record.scriptId, propertyId, record.script, value)
  return 1
}

@JsExport
fun kanamaWebCallInt(objectId: Int, methodId: Int, value: Int): Int {
  val record = instances.require(objectId)
  return KanamaWebProjectRegistry.callLong(record.scriptId, methodId, record.script, value.toLong())
    .toInt()
}

@JsExport
fun kanamaWebFree(objectId: Int): Int {
  clearWebPositionSnapshot(objectId)
  return if (instances.free(objectId)) 1 else 0
}

@JsExport
fun kanamaWebLoadPositionSnapshot(objectId: Int, x: Double, y: Double): Int {
  loadWebPositionSnapshot(objectId, x, y)
  return 1
}

@OptIn(InternalKanamaBackendApi::class)
@JsExport
fun kanamaWebBenchmarkBackendContract(objectId: Int, operations: Int): Int {
  require(operations in 1..WebCommandBuffer.BENCHMARK_COMMAND_CAPACITY)
  val handle = GodotHandle.fromBackendToken(objectId.toLong())
  val node = Node2DBackendContractProbe(handle)
  val initial = node.position
  repeat(operations) { check(node.position == initial) }
  val finalPosition = GodotVector2((operations - 1).toFloat(), initial.y)
  repeat(operations) { node.position = finalPosition }
  check(node.position == finalPosition)
  return node.getChildCount(false).toInt()
}

@JsExport
fun kanamaWebBenchmarkPure(iterations: Int): Double {
  var checksum = 0
  val started = webNowMillis()
  repeat(iterations) { checksum = (checksum * 31) xor it }
  val elapsed = webNowMillis() - started
  check(checksum != Int.MIN_VALUE) // keep the loop observable without exporting benchmark state
  return elapsed
}

@JsExport
fun kanamaWebBenchmarkBatch(objectId: Int, operations: Int): Double {
  instances.require(objectId)
  require(operations in 1..WebCommandBuffer.BENCHMARK_COMMAND_CAPACITY)
  commands.clear()
  val started = webNowMillis()
  repeat(operations) { commands.appendScalarMutation(objectId, it) }
  commands.flush()
  return webNowMillis() - started
}

fun main() {
  installWebCommonGodotBackend()
}

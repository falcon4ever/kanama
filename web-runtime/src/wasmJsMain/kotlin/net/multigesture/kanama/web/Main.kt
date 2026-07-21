@file:OptIn(ExperimentalJsExport::class, ExperimentalWasmJsInterop::class)

package net.multigesture.kanama.web

import kotlin.js.ExperimentalJsExport
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsExport
import net.multigesture.kanama.web.generated.KanamaWebProjectRegistry

private val instances = WebInstanceRegistry(KanamaWebProjectRegistry::create)
private val commands = WebCommandBuffer(WebCommandBuffer.BENCHMARK_COMMAND_CAPACITY)
private var frameSequence = 0

@JsExport fun kanamaWebProtocolVersion(): Int = KanamaWebProjectRegistry.PROTOCOL_VERSION

@JsExport fun kanamaWebRoundTrip(value: Int): Int = value

@JsExport fun kanamaWebCreate(scriptId: Int): Int = instances.create(scriptId)

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

@JsExport fun kanamaWebFree(objectId: Int): Int = if (instances.free(objectId)) 1 else 0

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
  // Keep startup side-effect free. Godot's custom HTML loader owns initialization ordering.
}

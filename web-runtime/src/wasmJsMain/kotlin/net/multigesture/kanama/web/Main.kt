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
internal val drawCommands = WebCommandBuffer(WebCommandBuffer.DRAW_COMMAND_CAPACITY)
private var frameSequence = 0

private inline fun <T> webCallbackBoundary(
  objectId: Int,
  callback: String,
  memberKind: String? = null,
  memberId: Int = 0,
  block: (WebScriptRecord) -> T,
): T {
  var scriptName = "<unresolved>"
  var memberName = callback
  try {
    val record = instances.require(objectId)
    val descriptor = KanamaWebProjectRegistry.scripts.firstOrNull { it.id == record.scriptId }
    scriptName = descriptor?.className ?: "script#${record.scriptId}"
    memberName =
      when (memberKind) {
        "method" -> descriptor?.methods?.firstOrNull { it.id == memberId }?.name
        "property" -> descriptor?.properties?.firstOrNull { it.id == memberId }?.name
        else -> null
      } ?: memberKind?.let { "$it#$memberId" } ?: callback
    return block(record)
  } catch (error: Throwable) {
    throw IllegalStateException(
      "Kanama Web callback failed: script=$scriptName handle=$objectId callback=$callback member=$memberName",
      error,
    )
  }
}

@JsExport fun kanamaWebProtocolVersion(): Int = KanamaWebProjectRegistry.PROTOCOL_VERSION

@JsExport fun kanamaWebRoundTrip(value: Int): Int = value

@JsExport
fun kanamaWebCreate(scriptId: Int): Int {
  return try {
    instances.create(scriptId)
  } catch (error: Throwable) {
    val scriptName =
      KanamaWebProjectRegistry.scripts.firstOrNull { it.id == scriptId }?.className
        ?: "script#$scriptId"
    throw IllegalStateException("Kanama Web create failed: script=$scriptName", error)
  }
}

@JsExport fun kanamaWebIsLive(objectHandle: Int): Int = if (instances.isLive(objectHandle)) 1 else 0

@JsExport
fun kanamaWebReady(objectId: Int): Int {
  return webCallbackBoundary(objectId, "_ready") { record ->
    KanamaWebProjectRegistry.ready(record.scriptId, record.script)
    commands.flush()
    1
  }
}

@JsExport
fun kanamaWebProcess(objectId: Int, delta: Double): Int {
  return webCallbackBoundary(objectId, "_process") { record ->
    commands.clear()
    KanamaWebProjectRegistry.process(record.scriptId, record.script, delta)
    commands.flush()
  }
}

/** Synthetic transport benchmark path; real Web scripts use [kanamaWebProcess]. */
@JsExport
fun kanamaWebSpikeProcess(objectId: Int, delta: Double): Int {
  return webCallbackBoundary(objectId, "_process") { record ->
    commands.clear()
    KanamaWebProjectRegistry.process(record.scriptId, record.script, delta)
    frameSequence += 1
    commands.appendScalarMutation(objectId, frameSequence)
    commands.flush()
  }
}

@JsExport
fun kanamaWebDraw(objectId: Int): Int {
  return webCallbackBoundary(objectId, "_draw") { record ->
    drawCommands.clear()
    KanamaWebProjectRegistry.draw(record.scriptId, record.script)
    drawCommands.flush()
  }
}

@JsExport
fun kanamaWebEmptyFrame(objectId: Int, delta: Double): Int {
  instances.require(objectId)
  return if (delta >= 0.0) 1 else 0
}

@JsExport
fun kanamaWebGetStringProperty(objectId: Int, propertyId: Int): String {
  return webCallbackBoundary(objectId, "property_get", "property", propertyId) { record ->
    KanamaWebProjectRegistry.getStringProperty(record.scriptId, propertyId, record.script)
  }
}

@JsExport
fun kanamaWebSetStringProperty(objectId: Int, propertyId: Int, value: String): Int {
  return webCallbackBoundary(objectId, "property_set", "property", propertyId) { record ->
    KanamaWebProjectRegistry.setStringProperty(record.scriptId, propertyId, record.script, value)
    1
  }
}

@JsExport
fun kanamaWebCallInt(objectId: Int, methodId: Int, value: Int): Int {
  return webCallbackBoundary(objectId, "registered_function", "method", methodId) { record ->
    KanamaWebProjectRegistry.callLong(record.scriptId, methodId, record.script, value.toLong())
      .toInt()
  }
}

@JsExport
fun kanamaWebCallNoArgs(objectId: Int, methodId: Int): Int {
  return webCallbackBoundary(objectId, "registered_function", "method", methodId) { record ->
    KanamaWebProjectRegistry.callNoArgs(record.scriptId, methodId, record.script)
    commands.flush()
  }
}

@JsExport
fun kanamaWebFree(objectId: Int): Int {
  return webCallbackBoundary(objectId, "_exit_tree") { record ->
    KanamaWebProjectRegistry.exitTree(record.scriptId, record.script)
    commands.flush()
    drawCommands.clear()
    clearWebPositionSnapshot(objectId)
    if (instances.free(objectId)) 1 else 0
  }
}

@JsExport
fun kanamaWebLoadPositionSnapshot(objectId: Int, x: Double, y: Double): Int {
  loadWebPositionSnapshot(objectId, x, y)
  return 1
}

@JsExport
fun kanamaWebLoadViewportRectSnapshot(
  objectId: Int,
  x: Double,
  y: Double,
  width: Double,
  height: Double,
): Int {
  loadWebViewportRectSnapshot(objectId, x, y, width, height)
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
  val viewportRect = node.viewportRect
  repeat(operations) { check(node.viewportRect == viewportRect) }
  val finalPosition = GodotVector2((operations - 1).toFloat(), initial.y)
  repeat(operations) { node.position = finalPosition }
  node.queueRedraw()
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

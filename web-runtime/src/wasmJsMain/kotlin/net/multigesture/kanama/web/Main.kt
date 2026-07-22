@file:OptIn(ExperimentalJsExport::class, ExperimentalWasmJsInterop::class)

package net.multigesture.kanama.web

import kotlin.js.ExperimentalJsExport
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsExport
import net.multigesture.kanama.api.WebFrameCoroutineDispatcher
import net.multigesture.kanama.backend.CanvasItemBackendContractProbe
import net.multigesture.kanama.backend.GodotColor
import net.multigesture.kanama.backend.GodotHandle
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.backend.Node2DBackendContractProbe
import net.multigesture.kanama.backend.NodeLookupBackendContractProbe
import net.multigesture.kanama.backend.Sprite2DBackendContractProbe
import net.multigesture.kanama.web.generated.KanamaWebProjectRegistry

internal val instances = WebInstanceRegistry(KanamaWebProjectRegistry::create)
internal val commands = WebCommandBuffer(WebCommandBuffer.BENCHMARK_COMMAND_CAPACITY)
internal val drawCommands = WebCommandBuffer(WebCommandBuffer.DRAW_COMMAND_CAPACITY)
private var frameSequence = 0
private var activeWebScriptHandle = 0

internal fun requireActiveWebScriptHandle(): Int =
  activeWebScriptHandle.takeIf { it != 0 }
    ?: error("A Kanama Web singleton call was made outside a script callback")

@PublishedApi
internal actual fun webScriptInstance(objectId: Int): Any? {
  return objectId.takeIf(instances::isLive)?.let { instances.require(it).script }
}

private inline fun <T> webCallbackBoundary(
  objectId: Int,
  callback: String,
  memberKind: String? = null,
  memberId: Int = 0,
  block: (WebScriptRecord) -> T,
): T {
  var scriptName = "<unresolved>"
  var memberName = callback
  val previousActiveHandle = activeWebScriptHandle
  try {
    val record = instances.require(objectId)
    activeWebScriptHandle = objectId
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
    val causeDetail = error.message ?: error::class.simpleName ?: "unknown error"
    throw IllegalStateException(
      "Kanama Web callback failed: script=$scriptName handle=$objectId callback=$callback " +
        "member=$memberName cause=$causeDetail",
      error,
    )
  } finally {
    activeWebScriptHandle = previousActiveHandle
  }
}

@JsExport fun kanamaWebProtocolVersion(): Int = KanamaWebProjectRegistry.PROTOCOL_VERSION

@JsExport fun kanamaWebRoundTrip(value: Int): Int = value

@JsExport fun kanamaWebPendingCoroutineCount(): Int = WebFrameCoroutineDispatcher.pendingCount

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
fun kanamaWebAdoptNodeHandle(objectHandle: Int): Int {
  registerWebBrowserHandle(objectHandle, WebBrowserHandleKind.NODE)
  return 1
}

@JsExport
fun kanamaWebAdoptObjectHandle(objectHandle: Int): Int {
  registerWebBrowserHandle(objectHandle, WebBrowserHandleKind.OBJECT)
  return 1
}

@JsExport
fun kanamaWebDiscardNodeHandle(objectHandle: Int): Int {
  clearWebPositionSnapshot(objectHandle)
  unregisterWebBrowserHandle(objectHandle, WebBrowserHandleKind.NODE)
  return 1
}

@JsExport
fun kanamaWebDiscardBrowserHandle(objectHandle: Int): Int =
  if (discardWebBrowserHandle(objectHandle)) 1 else 0

@JsExport
fun kanamaWebReady(objectId: Int): Int {
  return webCallbackBoundary(objectId, "_ready") { record ->
    KanamaWebProjectRegistry.ready(record.scriptId, record.script)
    commands.flush()
    1
  }
}

@JsExport
fun kanamaWebInput(objectId: Int, eventHandle: Int): Int {
  return webCallbackBoundary(objectId, "_input") { record ->
    KanamaWebProjectRegistry.input(record.scriptId, record.script, eventHandle)
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
fun kanamaWebSetLongProperty(objectId: Int, propertyId: Int, value: Double): Int {
  require(value.isFinite() && value % 1.0 == 0.0) { "Web integer property must be integral" }
  return webCallbackBoundary(objectId, "property_set", "property", propertyId) { record ->
    KanamaWebProjectRegistry.setLongProperty(
      record.scriptId,
      propertyId,
      record.script,
      value.toLong(),
    )
    1
  }
}

@JsExport
fun kanamaWebSetObjectProperty(objectId: Int, propertyId: Int, value: Int): Int {
  if (value != 0) registerWebBrowserHandle(value, WebBrowserHandleKind.RESOURCE)
  return webCallbackBoundary(objectId, "property_set", "property", propertyId) { record ->
    KanamaWebProjectRegistry.setObjectProperty(record.scriptId, propertyId, record.script, value)
    1
  }
}

@JsExport
fun kanamaWebSetObjectArrayProperty(objectId: Int, propertyId: Int, encodedValues: String): Int {
  val values =
    encodedValues.takeIf { it.isNotEmpty() }?.split(',')?.map(String::toInt)?.toIntArray()
      ?: IntArray(0)
  values.forEach { registerWebBrowserHandle(it, WebBrowserHandleKind.RESOURCE) }
  return webCallbackBoundary(objectId, "property_set", "property", propertyId) { record ->
    KanamaWebProjectRegistry.setObjectArrayProperty(
      record.scriptId,
      propertyId,
      record.script,
      values,
    )
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
fun kanamaWebCallVector2i(objectId: Int, methodId: Int, x: Int, y: Int): Int {
  return webCallbackBoundary(objectId, "registered_function", "method", methodId) { record ->
    KanamaWebProjectRegistry.callVector2i(record.scriptId, methodId, record.script, x, y)
    commands.flush()
    1
  }
}

@JsExport
fun kanamaWebCallObjectObjectLong(
  objectId: Int,
  methodId: Int,
  firstHandle: Int,
  secondHandle: Int,
  value: Double,
): Int {
  require(value.isFinite() && value % 1.0 == 0.0) { "Web integer argument must be integral" }
  return webCallbackBoundary(objectId, "registered_function", "method", methodId) { record ->
    KanamaWebProjectRegistry.callObjectObjectLong(
      record.scriptId,
      methodId,
      record.script,
      firstHandle,
      secondHandle,
      value.toLong(),
    )
    commands.flush()
    1
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
fun kanamaWebLoadNode2DSnapshot(
  objectId: Int,
  positionX: Double,
  positionY: Double,
  scaleX: Double,
  scaleY: Double,
  modulateR: Double,
  modulateG: Double,
  modulateB: Double,
  modulateA: Double,
): Int {
  loadWebNode2DSnapshot(
    objectId,
    positionX,
    positionY,
    scaleX,
    scaleY,
    modulateR,
    modulateG,
    modulateB,
    modulateA,
  )
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
fun kanamaWebMatch3Group3Probe(tileObjectId: Int): Int {
  val tileHandle = GodotHandle.fromBackendToken(tileObjectId.toLong())
  val tile = Node2DBackendContractProbe(tileHandle)
  val spriteHandle =
    checkNotNull(NodeLookupBackendContractProbe(tileHandle).getNodeOrNull("Sprite2D"))
  val sprite = Node2DBackendContractProbe(spriteHandle)
  val canvas = CanvasItemBackendContractProbe(spriteHandle)
  val texture = Sprite2DBackendContractProbe(spriteHandle)
  val originalPosition = tile.position
  val originalScale = sprite.scale
  val originalModulate = canvas.modulate
  val originalTexture = texture.getTexture()
  val testPosition = GodotVector2(originalPosition.x + 7.0f, originalPosition.y - 5.0f)
  val testScale = GodotVector2(1.25f, 0.75f)
  val testModulate = GodotColor(0.8f, 0.7f, 0.6f, 0.5f)
  tile.position = testPosition
  sprite.scale = testScale
  canvas.modulate = testModulate

  var result = 0
  if (tile.position == testPosition) result = result or 1
  if (sprite.scale == testScale) result = result or 2
  if (canvas.modulate == testModulate) result = result or 4
  if (
    originalTexture != null &&
      texture.getTexture()?.backendToken() == originalTexture.backendToken()
  ) {
    result = result or 8
  }

  tile.position = originalPosition
  sprite.scale = originalScale
  canvas.modulate = originalModulate
  if (
    tile.position == originalPosition &&
      sprite.scale == originalScale &&
      canvas.modulate == originalModulate
  ) {
    result = result or 16
  }
  commands.flush()
  return result
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

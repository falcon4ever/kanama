@file:OptIn(ExperimentalJsExport::class, ExperimentalWasmJsInterop::class)

package net.multigesture.kanama.web

import kotlin.js.ExperimentalJsExport
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsExport
import kotlinx.coroutines.launch
import net.multigesture.kanama.api.AudioStreamPlayer
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScope
import net.multigesture.kanama.api.MainThread
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.Node2D
import net.multigesture.kanama.api.SceneTree
import net.multigesture.kanama.api.Tween
import net.multigesture.kanama.api.WebFrameCoroutineDispatcher
import net.multigesture.kanama.api.WebFrameScheduler
import net.multigesture.kanama.api.WebSignalCallbackRegistry
import net.multigesture.kanama.api.webFrameSchedulerStateProbe
import net.multigesture.kanama.backend.CanvasItemBackendContractProbe
import net.multigesture.kanama.backend.GPUParticles2DBackendContractProbe
import net.multigesture.kanama.backend.GodotColor
import net.multigesture.kanama.backend.GodotHandle
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.backend.Node2DBackendContractProbe
import net.multigesture.kanama.backend.NodeLookupBackendContractProbe
import net.multigesture.kanama.backend.Sprite2DBackendContractProbe
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.web.generated.KanamaWebProjectRegistry

internal val instances = WebInstanceRegistry(KanamaWebProjectRegistry::create)
internal val commands = WebCommandBuffer(WebCommandBuffer.BENCHMARK_COMMAND_CAPACITY)
internal val drawCommands = WebCommandBuffer(WebCommandBuffer.DRAW_COMMAND_CAPACITY)
private var frameSequence = 0
private var activeWebScriptHandle = 0
private var group6ProbeMask = 0
private var group6ProbeScope: KanamaScope? = null
private var group6ProbeOwnerHandle = 0
private var group7AudioFreedPlayerHandle = 0
private var group8SceneTreeHandle = 0

internal fun requireActiveWebScriptHandle(): Int =
  activeWebScriptHandle.takeIf { it != 0 }
    ?: error("A Kanama Web singleton call was made outside a script callback")

private inline fun <T> withActiveWebScriptHandle(objectId: Int, block: () -> T): T {
  val previous = activeWebScriptHandle
  activeWebScriptHandle = objectId
  try {
    return block()
  } finally {
    activeWebScriptHandle = previous
  }
}

@PublishedApi
internal actual fun webScriptInstance(objectId: Int): Any? {
  return objectId.takeIf(instances::isLive)?.let { instances.require(it).script }
}

private fun <T> webCallbackBoundary(
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
    return WebFrameScheduler.withOwner(objectId) {
      withActiveWebScriptHandle(objectId) { block(record) }
    }
  } catch (error: Throwable) {
    val causeDetail = error.message ?: error::class.simpleName ?: "unknown error"
    throw IllegalStateException(
      "Kanama Web callback failed: script=$scriptName handle=$objectId callback=$callback " +
        "member=$memberName cause=$causeDetail",
      error,
    )
  }
}

@JsExport fun kanamaWebProtocolVersion(): Int = KanamaWebProjectRegistry.PROTOCOL_VERSION

@JsExport fun kanamaWebRoundTrip(value: Int): Int = value

@JsExport fun kanamaWebPendingCoroutineCount(): Int = WebFrameCoroutineDispatcher.pendingCount

@JsExport fun kanamaWebRegisteredCoroutineJobCount(): Int = WebFrameScheduler.registeredJobCount

@JsExport fun kanamaWebPendingSignalCallbackCount(): Int = WebSignalCallbackRegistry.size

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

/** Match3's single frame pump; the JavaScript bridge invokes it only for the Main script. */
@JsExport
fun kanamaWebFrame(objectId: Int, delta: Double): Int {
  return webCallbackBoundary(objectId, "frame_scheduler") { record ->
    commands.clear()
    val executed =
      WebFrameScheduler.pump(delta, instances::isLive) { ownerHandle, action ->
        withActiveWebScriptHandle(ownerHandle, action)
      }
    KanamaWebProjectRegistry.process(record.scriptId, record.script, delta)
    commands.flush()
    executed
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
    WebSignalCallbackRegistry.releaseOwner(objectId)
    if (group6ProbeOwnerHandle == objectId) {
      group6ProbeScope?.cancel()
      group6ProbeScope = null
      group6ProbeOwnerHandle = 0
    }
    WebFrameScheduler.cancelOwner(objectId)
    if (instances.free(objectId)) 1 else 0
  }
}

@JsExport fun kanamaWebFrameSchedulerStateProbe(): Int = webFrameSchedulerStateProbe()

@JsExport
fun kanamaWebMatch3Group6Probe(objectId: Int): Int {
  return webCallbackBoundary(objectId, "group6_probe") {
    group6ProbeScope?.cancel()
    group6ProbeMask = 0
    val scope = KanamaScope()
    group6ProbeScope = scope
    group6ProbeOwnerHandle = objectId
    scope.launch {
      group6ProbeMask = group6ProbeMask or 1
      SceneTree.delaySeconds(0.05)
      group6ProbeMask = group6ProbeMask or 2
      MainThread.post {
        group6ProbeMask = group6ProbeMask or 4
        scope.cancel()
        if (group6ProbeScope === scope) {
          group6ProbeScope = null
          group6ProbeOwnerHandle = 0
        }
      }
    }
    1
  }
}

@JsExport fun kanamaWebMatch3Group6ProbeMask(): Int = group6ProbeMask

@JsExport
fun kanamaWebMatch3Group6CancellationProbe(objectId: Int): Int {
  return webCallbackBoundary(objectId, "group6_cancel_probe") {
    val before = WebFrameScheduler.pendingCount
    val scope = KanamaScope()
    scope.launch {
      SceneTree.delaySeconds(10.0)
      group6ProbeMask = group6ProbeMask or 8
    }
    var result = 0
    if (WebFrameScheduler.pendingCount == before + 1) result = result or 1
    scope.cancel()
    if (WebFrameScheduler.pendingCount == before) result = result or 2
    result
  }
}

@JsExport
fun kanamaWebDispatchSignal0(objectId: Int, callbackId: Int): Int {
  return webCallbackBoundary(objectId, "_kanama_web_signal_dispatch0") {
    WebSignalCallbackRegistry.dispatch(objectId, callbackId)
    1
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

@JsExport
fun kanamaWebLoadParticlesSnapshot(objectId: Int, emitting: Boolean, lifetime: Double): Int {
  loadWebParticlesSnapshot(objectId, emitting, lifetime)
  return 1
}

@JsExport fun kanamaWebParticlesSnapshotCount(): Int = webParticlesSnapshotCount()

@JsExport
fun kanamaWebMatch3Group7AudioProbe(
  audioObjectId: Int,
  playerObjectId: Int,
  soundPath: String,
): Int =
  webCallbackBoundary(audioObjectId, "group7_audio_probe") {
    commands.clear()
    val player = AudioStreamPlayer(WebObjectId(playerObjectId))
    player.setStreamFromPath(soundPath)
    player.setBus("master")
    player.setVolumeDb(-9.25)
    player.setPitchScale(1.125)
    player.play(0.0)
    player.play(0.25)
    commands.flush()
  }

@JsExport
fun kanamaWebMatch3Group7AudioMissingPathProbe(
  audioObjectId: Int,
  playerObjectId: Int,
  soundPath: String,
): Int =
  webCallbackBoundary(audioObjectId, "group7_audio_missing_path_probe") {
    commands.clear()
    AudioStreamPlayer(WebObjectId(playerObjectId)).setStreamFromPath(soundPath)
    commands.flush()
  }

@JsExport
fun kanamaWebMatch3Group7AudioTeardownProbe(audioObjectId: Int): Int =
  webCallbackBoundary(audioObjectId, "group7_audio_teardown_probe") {
    commands.clear()
    val owner = Node(WebObjectId(audioObjectId))
    val player = AudioStreamPlayer.create()
    group7AudioFreedPlayerHandle = player.handle.value
    owner.addChild(player)
    var result = if (commands.flush() == 1) 1 else 0
    player.queueFree()
    if (commands.flush() == 1) result = result or 2
    if (
      runCatching { AudioStreamPlayer(WebObjectId(group7AudioFreedPlayerHandle)).play() }.isFailure
    ) {
      result = result or 4
    }
    result
  }

@JsExport fun kanamaWebMatch3Group7AudioFreedPlayerHandle(): Int = group7AudioFreedPlayerHandle

@JsExport
fun kanamaWebMatch3Group8SceneTreeProbe(objectId: Int): Int =
  webCallbackBoundary(objectId, "group8_scene_tree_probe") {
    commands.clear()
    val node = Node(WebObjectId(objectId))
    val first = node.getTree()
    val second = node.getTree()
    group8SceneTreeHandle = first.handle.value
    var result = 0
    if (first.isSameInstance(second)) result = result or 1
    if (group8SceneTreeHandle > 0) result = result or 2
    result
  }

@JsExport fun kanamaWebMatch3Group8SceneTreeHandle(): Int = group8SceneTreeHandle

@JsExport
fun kanamaWebMatch3Group8SceneTreeQuitProbe(objectId: Int, exitCode: Int): Int =
  webCallbackBoundary(objectId, "group8_scene_tree_quit_probe") {
    commands.clear()
    Node(WebObjectId(objectId)).getTree().quit(exitCode.toLong())
    commands.flush()
  }

@JsExport
@OptIn(InternalKanamaBackendApi::class)
fun kanamaWebMatch3Group8SceneTreeStaleProbe(sceneTreeObjectId: Int): Int =
  if (
    runCatching { SceneTree(GodotHandle.fromBackendToken(sceneTreeObjectId.toLong())).quit() }
      .isFailure
  ) {
    1
  } else {
    0
  }

@JsExport
fun kanamaWebMatch3Group8MainTeardownProbe(objectId: Int): Int =
  webCallbackBoundary(objectId, "group8_main_teardown_probe") {
    commands.clear()
    Node(WebObjectId(objectId)).queueFree()
    commands.flush()
  }

@JsExport
fun kanamaWebMatch3Group7AudioOwnerTeardownProbe(audioObjectId: Int): Int =
  webCallbackBoundary(audioObjectId, "group7_audio_owner_teardown_probe") {
    commands.clear()
    Node(WebObjectId(audioObjectId)).queueFree()
    commands.flush()
  }

@JsExport
fun kanamaWebMatch3Group7AudioStaleProbe(playerObjectId: Int): Int =
  if (runCatching { AudioStreamPlayer(WebObjectId(playerObjectId)).play() }.isFailure) 1 else 0

@OptIn(InternalKanamaBackendApi::class)
@JsExport
fun kanamaWebMatch3Group7ParticleProbe(particleObjectId: Int): Int {
  val particle =
    GPUParticles2DBackendContractProbe(GodotHandle.fromBackendToken(particleObjectId.toLong()))
  var result = 0
  if (particle.emitting) result = result or 1
  particle.emitting = false
  if (!particle.emitting) result = result or 2
  particle.emitting = true
  if (particle.emitting) result = result or 4
  if (commands.flush() == 2) result = result or 8
  if (kotlin.math.abs(particle.lifetime - 1.0) <= 0.000_001) result = result or 16
  return result
}

@OptIn(InternalKanamaBackendApi::class)
@JsExport
fun kanamaWebMatch3Group7ParticleStaleProbe(particleObjectId: Int): Int =
  if (
    runCatching {
        GPUParticles2DBackendContractProbe(GodotHandle.fromBackendToken(particleObjectId.toLong()))
          .emitting
      }
      .isFailure
  ) {
    1
  } else {
    0
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
fun kanamaWebMatch3Group4Probe(tileObjectId: Int): Int {
  val tile = GodotObject(WebObjectId(tileObjectId))
  val signalSourceHandle =
    NodeLookupBackendContractProbe(tile.backendHandle).getNodeOrNull("Sprite2D") ?: return 0
  val signalSource = GodotObject(WebObjectId(signalSourceHandle.backendToken().toInt()))
  val callbacksBefore = WebSignalCallbackRegistry.size
  var callbackCalls = 0
  val connectResult =
    signalSource.signal("visibility_changed").connect(
      tile,
      argumentCount = 0,
      flags = GodotObject.CONNECT_ONE_SHOT,
    ) {
      callbackCalls += 1
    }
  val callbacksAfterConnect = WebSignalCallbackRegistry.size
  signalSource.emitSignal("visibility_changed")
  val callbacksAfterFirstEmit = WebSignalCallbackRegistry.size
  signalSource.emitSignal("visibility_changed")

  var result = 0
  if (connectResult == 0L) result = result or 1
  if (callbacksAfterConnect == callbacksBefore + 1) result = result or 2
  if (callbackCalls == 1) result = result or 4
  if (callbacksAfterFirstEmit == callbacksBefore) result = result or 8
  if (WebSignalCallbackRegistry.size == callbacksBefore) result = result or 16
  return result
}

@OptIn(InternalKanamaBackendApi::class)
@JsExport
fun kanamaWebMatch3Group5Probe(tileObjectId: Int): Int {
  val tile = Node(WebObjectId(tileObjectId))
  val spriteHandle =
    NodeLookupBackendContractProbe(tile.backendHandle).getNodeOrNull("Sprite2D") ?: return 0
  val sprite = Node2D(WebObjectId(spriteHandle.backendToken().toInt()))

  var result = 0
  val tween = tile.createTween() ?: return result
  result = result or 1
  if (tween.setParallel(true).isSameInstance(tween)) result = result or 2
  val scaleTweener = tween.tweenProperty(sprite, "scale", Vector2(1.05, 0.95), 0.05)
  if (scaleTweener != null) result = result or 4
  if (
    scaleTweener != null &&
      scaleTweener.setTrans(Tween.TRANS_BACK).isSameInstance(scaleTweener) &&
      scaleTweener.setEase(Tween.EASE_OUT).isSameInstance(scaleTweener)
  ) {
    result = result or 8
  }
  if (tween.tweenProperty(sprite, "modulate", Color(0.9f, 0.8f, 0.7f, 1.0f), 0.05) != null) {
    result = result or 16
  }
  if (
    tween.signal(Tween.Signals.finished).connect(
      tile,
      argumentCount = 0,
      flags = GodotObject.CONNECT_ONE_SHOT,
    ) {} == 0L
  ) {
    result = result or 32
  }

  val callbacksBeforeKilledTween = WebSignalCallbackRegistry.size
  var killedCallbackCalls = 0
  val killedTween = tile.createTween()
  val killedConnection =
    killedTween?.signal(Tween.Signals.finished)?.connect(
      tile,
      argumentCount = 0,
      flags = GodotObject.CONNECT_ONE_SHOT,
    ) {
      killedCallbackCalls += 1
    }
  if (killedTween != null && killedTween.tweenProperty(sprite, "scale", Vector2.ONE, 1.0) != null) {
    killedTween.kill()
    result = result or 64
  }
  if (
    killedConnection == 0L &&
      killedCallbackCalls == 0 &&
      WebSignalCallbackRegistry.size == callbacksBeforeKilledTween
  ) {
    result = result or 128
  }
  return result
}

@OptIn(InternalKanamaBackendApi::class)
@JsExport
fun kanamaWebMatch3Group5SnapshotProbe(
  tileObjectId: Int,
  expectedScaleX: Double,
  expectedScaleY: Double,
  expectedR: Double,
  expectedG: Double,
  expectedB: Double,
  expectedA: Double,
): Int {
  val tile = Node(WebObjectId(tileObjectId))
  val spriteHandle =
    NodeLookupBackendContractProbe(tile.backendHandle).getNodeOrNull("Sprite2D") ?: return 0
  val sprite = Node2D(WebObjectId(spriteHandle.backendToken().toInt()))
  val scale = sprite.scale
  val modulate = sprite.modulate
  var result = 0
  if (
    kotlin.math.abs(scale.x - expectedScaleX) <= 0.01 &&
      kotlin.math.abs(scale.y - expectedScaleY) <= 0.01
  ) {
    result = result or 1
  }
  if (
    kotlin.math.abs(modulate.r.toDouble() - expectedR) <= 0.01 &&
      kotlin.math.abs(modulate.g.toDouble() - expectedG) <= 0.01 &&
      kotlin.math.abs(modulate.b.toDouble() - expectedB) <= 0.01 &&
      kotlin.math.abs(modulate.a.toDouble() - expectedA) <= 0.01
  ) {
    result = result or 2
  }
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

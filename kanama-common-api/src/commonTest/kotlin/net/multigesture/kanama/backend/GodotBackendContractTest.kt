package net.multigesture.kanama.backend

import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@OptIn(InternalKanamaBackendApi::class)
class GodotBackendContractTest {
  @AfterTest
  fun resetBackend() {
    GodotBackendCalls.resetForTests()
  }

  @Test
  fun initialDescriptorsPinHashesShapesAndExecutionModes() {
    assertEquals(894402480L, InitialGodotCallDescriptors.NODE_GET_CHILD_COUNT.hash)
    assertEquals(
      GodotExecutionMode.IMMEDIATE_RESULT,
      InitialGodotCallDescriptors.NODE_GET_CHILD_COUNT.executionMode,
    )
    assertEquals(3341600327L, InitialGodotCallDescriptors.NODE2D_GET_POSITION.hash)
    assertEquals(
      GodotExecutionMode.SNAPSHOT_READ,
      InitialGodotCallDescriptors.NODE2D_GET_POSITION.executionMode,
    )
    assertEquals(743155724L, InitialGodotCallDescriptors.NODE2D_SET_POSITION.hash)
    assertEquals(
      GodotExecutionMode.QUEUED_MUTATION,
      InitialGodotCallDescriptors.NODE2D_SET_POSITION.executionMode,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.GPUPARTICLES2D_SET_EMITTING,
      2_586_408_642L,
      GodotCallShape.BOOL_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.GPUPARTICLES2D_IS_EMITTING,
      36_873_697L,
      GodotCallShape.NOARGS_RET_BOOL,
      GodotExecutionMode.SNAPSHOT_READ,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.GPUPARTICLES2D_GET_LIFETIME,
      1_740_695_150L,
      GodotCallShape.NOARGS_RET_DOUBLE,
      GodotExecutionMode.SNAPSHOT_READ,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.AUDIOSTREAMPLAYER_SET_STREAM,
      2_210_767_741L,
      GodotCallShape.OBJECT_ARG,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.AUDIOSTREAMPLAYER_SET_BUS,
      3_304_788_590L,
      GodotCallShape.STRINGNAME_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.AUDIOSTREAMPLAYER_SET_VOLUME_DB,
      373_806_689L,
      GodotCallShape.DOUBLE_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.AUDIOSTREAMPLAYER_SET_PITCH_SCALE,
      373_806_689L,
      GodotCallShape.DOUBLE_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.AUDIOSTREAMPLAYER_PLAY,
      1_958_160_172L,
      GodotCallShape.DOUBLE_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.NODE_GET_TREE,
      2_958_820_483L,
      GodotCallShape.NOARGS_RET_HANDLE,
      GodotExecutionMode.IMMEDIATE_RESULT,
    )
    assertDescriptor(
      InitialGodotCallDescriptors.SCENETREE_QUIT,
      1_995_695_955L,
      GodotCallShape.LONG_ARG,
      GodotExecutionMode.QUEUED_MUTATION,
    )
  }

  @Test
  fun commonProbeUsesTypedCallsAndCachesResolvedCallSites() {
    val backend = RecordingBackend()
    GodotBackendCalls.install(backend)
    val probe = Node2DBackendContractProbe(GodotHandle.fromBackendToken(17))

    assertEquals(GodotVector2(1.0f, 2.0f), probe.position)
    assertEquals(GodotVector2(1.0f, 2.0f), probe.position)
    probe.position = GodotVector2(3.0f, 4.0f)
    assertEquals(GodotVector2(3.0f, 4.0f), probe.position)
    assertEquals(GodotVector2(1.0f, 1.0f), probe.scale)
    probe.scale = GodotVector2(1.25f, 0.75f)
    assertEquals(GodotVector2(1.25f, 0.75f), probe.scale)
    val canvas = CanvasItemBackendContractProbe(probe.handle)
    assertEquals(GodotColor(1.0f, 1.0f, 1.0f, 1.0f), canvas.modulate)
    canvas.modulate = GodotColor(0.8f, 0.7f, 0.6f, 0.5f)
    assertEquals(GodotColor(0.8f, 0.7f, 0.6f, 0.5f), canvas.modulate)
    assertEquals(
      GodotRect2(GodotVector2(0.0f, 0.0f), GodotVector2(640.0f, 480.0f)),
      probe.viewportRect,
    )
    probe.queueRedraw()
    probe.drawTexture(
      GodotHandle.fromBackendToken(23),
      GodotVector2(12.0f, 34.0f),
      GodotColor(1.0f, 0.5f, 0.25f),
    )
    val texture = ResourceLoaderBackendContractProbe.load("res://bunny.svg", "Texture2D")
    assertEquals(31L, texture?.backendToken())
    val sprite = ClassDBBackendContractProbe.instantiate("Sprite2D")
    assertEquals(41L, sprite?.backendToken())
    val node = NodeBackendContractProbe(probe.handle)
    node.addChild(checkNotNull(sprite))
    val sceneTree = checkNotNull(node.getTree())
    assertEquals(71L, sceneTree.backendToken())
    SceneTreeBackendContractProbe(sceneTree).quit(7L)
    assertFailsWith<IllegalArgumentException> {
      SceneTreeBackendContractProbe(sceneTree).quit(Int.MAX_VALUE.toLong() + 1L)
    }
    Sprite2DBackendContractProbe(sprite).setTexture(texture)
    assertEquals(31L, Sprite2DBackendContractProbe(sprite).getTexture()?.backendToken())
    node.removeChild(sprite)
    NodeBackendContractProbe(sprite).queueFree()
    val board = NodeLookupBackendContractProbe(probe.handle).getNodeOrNull("Board")
    assertEquals(51L, board?.backendToken())
    val viewport = NodeLookupBackendContractProbe(probe.handle).getViewport()
    assertEquals(52L, viewport?.backendToken())
    assertEquals(
      GodotRect2(GodotVector2(0.0f, 0.0f), GodotVector2(640.0f, 480.0f)),
      ViewportBackendContractProbe(checkNotNull(viewport)).visibleRect,
    )
    val tile = PackedSceneBackendContractProbe(checkNotNull(texture)).instantiate()
    assertEquals(53L, tile?.backendToken())
    InputBackendContractProbe.setCustomMouseCursor(texture)
    assertEquals(
      0L,
      SignalBackendContractProbe(checkNotNull(tile))
        .connect(probe.handle, "tile_pressed", "_on_tile_pressed"),
    )
    assertEquals(true, GodotObjectBackendContractProbe(tile).isClass("InputEventMouseButton"))
    assertEquals(true, InputEventBackendContractProbe(tile).isPressed())
    assertEquals(false, InputEventBackendContractProbe(tile).isReleased())
    assertEquals(1L, InputEventMouseButtonBackendContractProbe(tile).getButtonIndex())
    assertEquals(
      GodotVector2(320.0f, 240.0f),
      CanvasItemInputBackendContractProbe(checkNotNull(board)).getLocalMousePosition(),
    )
    assertEquals(
      0,
      SignalBackendContractProbe(tile).emitVector2i("tile_pressed", GodotVector2i(3, 4)),
    )
    assertEquals(
      0L,
      SignalBackendContractProbe(tile)
        .connectBound(tile, "finished", "_kanama_web_signal_dispatch0", 71L, 4L),
    )
    assertEquals(0, SignalBackendContractProbe(tile).emitNoArgs("finished"))
    val tween = checkNotNull(NodeBackendContractProbe(probe.handle).createTween())
    assertEquals(61L, tween.backendToken())
    val tweenProbe = TweenBackendContractProbe(tween)
    assertEquals(61L, tweenProbe.setParallel()?.backendToken())
    val vectorTweener =
      checkNotNull(
        tweenProbe.tweenProperty(probe.handle, "position", GodotVector2(12.0f, 34.0f), 0.3)
      )
    assertEquals(62L, vectorTweener.backendToken())
    assertEquals(
      63L,
      tweenProbe
        .tweenProperty(probe.handle, "modulate", GodotColor(0.5f, 0.6f, 0.7f, 0.8f), 0.1)
        ?.backendToken(),
    )
    val propertyTweener = PropertyTweenerBackendContractProbe(vectorTweener)
    assertEquals(62L, propertyTweener.setTrans(10L)?.backendToken())
    assertEquals(62L, propertyTweener.setEase(1L)?.backendToken())
    tweenProbe.kill()
    val particles = GPUParticles2DBackendContractProbe(tile)
    assertEquals(false, particles.emitting)
    particles.emitting = true
    assertEquals(true, particles.emitting)
    assertEquals(true, particles.emitting)
    assertEquals(1.0, particles.lifetime)
    assertEquals(1.0, particles.lifetime)
    val audioStream =
      ResourceLoaderBackendContractProbe.load("res://sounds/tile-swap.ogg", "AudioStream")
    assertEquals(31L, audioStream?.backendToken())
    val audio =
      AudioStreamPlayerBackendContractProbe(
        checkNotNull(AudioStreamPlayerBackendContractProbe.create())
      )
    audio.setStream(audioStream)
    audio.setStream(null)
    audio.setBus("master")
    audio.setVolumeDb(-10.0)
    audio.setPitchScale(1.2)
    audio.play()
    assertFailsWith<IllegalArgumentException> { audio.setPitchScale(Double.NaN) }
    GDBackendContractProbe.randomize()
    assertEquals(4_294_967_295L, GDBackendContractProbe.randi())
    assertEquals(0.75, GDBackendContractProbe.randf())
    assertEquals(0, probe.emitSignal("benchmark_finished", 42))
    assertEquals(7L, probe.getChildCount())

    assertEquals(
      mapOf(
        1 to 1,
        2 to 1,
        3 to 1,
        4 to 1,
        5 to 1,
        6 to 1,
        7 to 1,
        8 to 1,
        9 to 1,
        10 to 1,
        11 to 1,
        12 to 1,
        13 to 1,
        14 to 1,
        15 to 1,
        16 to 1,
        17 to 1,
        18 to 1,
        19 to 1,
        20 to 1,
        21 to 1,
        22 to 1,
        23 to 1,
        24 to 1,
        25 to 1,
        26 to 1,
        27 to 1,
        28 to 1,
        29 to 1,
        30 to 1,
        31 to 1,
        32 to 1,
        33 to 1,
        34 to 1,
        35 to 1,
        36 to 1,
        37 to 1,
        38 to 1,
        39 to 1,
        40 to 1,
        41 to 1,
        42 to 1,
        43 to 1,
        44 to 1,
        45 to 1,
        46 to 1,
        47 to 1,
        48 to 1,
        49 to 1,
        50 to 1,
        51 to 1,
        52 to 1,
      ),
      backend.resolveCounts,
    )
    assertEquals(1, backend.queuedRedraws)
    assertEquals(
      DrawCall(
        textureToken = 23,
        position = GodotVector2(12.0f, 34.0f),
        modulate = GodotColor(1.0f, 0.5f, 0.25f),
      ),
      backend.drawCall,
    )
    assertEquals(1, backend.randomizeCalls)
    assertEquals("benchmark_finished" to 42, backend.emittedSignal)
    assertEquals("tile_pressed" to GodotVector2i(3, 4), backend.emittedVectorSignal)
    assertEquals(listOf("Sprite2D", "AudioStreamPlayer"), backend.constructedClasses)
    assertEquals(
      listOf(
        Triple("res://bunny.svg", "Texture2D", 1L),
        Triple("res://sounds/tile-swap.ogg", "AudioStream", 1L),
      ),
      backend.resourceLoads,
    )
    assertEquals(Triple(17L, 41L, false), backend.addedChild)
    assertEquals(17L to 41L, backend.removedChild)
    assertEquals(listOf(41L to 31L, 41L to 31L, 41L to null), backend.objectArguments)
    assertEquals("master", backend.stringNameArgument)
    assertEquals(listOf(48 to -10.0, 49 to 1.2, 50 to 0.0), backend.doubleArguments)
    assertEquals(71L to 7L, backend.longArgument)
    assertEquals(41L, backend.queuedFree)
  }

  private data class DrawCall(
    val textureToken: Long,
    val position: GodotVector2,
    val modulate: GodotColor,
  )

  private fun assertDescriptor(
    descriptor: GodotCallDescriptor,
    hash: Long,
    shape: GodotCallShape,
    executionMode: GodotExecutionMode,
  ) {
    assertEquals(hash, descriptor.hash)
    assertEquals(shape, descriptor.shape)
    assertEquals(executionMode, descriptor.executionMode)
    assertEquals(GodotReturnOwnership.BORROWED, descriptor.returnOwnership)
  }

  private class RecordingBackend : GodotBackendSpi {
    val resolveCounts = mutableMapOf<Int, Int>()
    private var position = GodotVector2(1.0f, 2.0f)
    private var scale = GodotVector2(1.0f, 1.0f)
    private var modulate = GodotColor(1.0f, 1.0f, 1.0f, 1.0f)
    var queuedRedraws = 0
    var drawCall: DrawCall? = null
    var randomizeCalls = 0
    var emittedSignal: Pair<String, Int>? = null
    var emittedVectorSignal: Pair<String, GodotVector2i>? = null
    val constructedClasses = mutableListOf<String>()
    val resourceLoads = mutableListOf<Triple<String, String, Long>>()
    var addedChild: Triple<Long, Long, Boolean>? = null
    var removedChild: Pair<Long, Long>? = null
    val objectArguments = mutableListOf<Pair<Long, Long?>>()
    var stringNameArgument: String? = null
    val doubleArguments = mutableListOf<Pair<Int, Double>>()
    var longArgument: Pair<Long, Long>? = null
    var queuedFree: Long? = null
    private var particlesEmitting = false

    override fun requireLive(handle: GodotHandle) {
      require(handle.backendToken() in setOf(17L, 31L, 41L, 51L, 52L, 53L, 61L, 62L, 63L, 71L))
    }

    override fun resolve(descriptor: GodotCallDescriptor): GodotCallSite {
      resolveCounts[descriptor.opcode] = resolveCounts.getOrElse(descriptor.opcode) { 0 } + 1
      return GodotCallSite.fromBackendToken(descriptor.opcode.toLong())
    }

    override fun invokeBoolRetInt(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: Boolean,
    ): Int = 7

    override fun invokeBoolRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: Boolean,
    ): GodotHandle? {
      assertEquals(61L, receiver.backendToken())
      assertEquals(true, value)
      return receiver
    }

    override fun invokeBoolArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: Boolean,
    ) {
      assertEquals(43, descriptor.opcode)
      particlesEmitting = value
    }

    override fun invokeDoubleArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: Double,
    ) {
      doubleArguments += descriptor.opcode to value
    }

    override fun invokeNoArgsRetVector2(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): GodotVector2 =
      when (descriptor.opcode) {
        27 -> GodotVector2(320.0f, 240.0f)
        29 -> scale
        else -> position
      }

    override fun invokeVector2Arg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: GodotVector2,
    ) {
      when (descriptor.opcode) {
        30 -> scale = value
        else -> position = value
      }
    }

    override fun invokeNoArgsRetRect2(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): GodotRect2 = GodotRect2(GodotVector2(0.0f, 0.0f), GodotVector2(640.0f, 480.0f))

    override fun invokeNoArgsVoid(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ) {
      when (descriptor.opcode) {
        5 -> queuedRedraws += 1
        15 -> queuedFree = receiver.backendToken()
        37 -> assertEquals(61L, receiver.backendToken())
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
      drawCall = DrawCall(texture.backendToken(), position, modulate)
    }

    override fun invokeStringStringLongRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      first: String,
      second: String,
      value: Long,
    ): GodotHandle? {
      assertEquals(1L, value)
      resourceLoads += Triple(first, second, value)
      return GodotHandle.fromBackendToken(31)
    }

    override fun invokeUtilityNoArgsVoid(descriptor: GodotCallDescriptor, callSite: GodotCallSite) {
      randomizeCalls += 1
    }

    override fun invokeUtilityNoArgsRetLong(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
    ): Long = 4_294_967_295L

    override fun invokeUtilityNoArgsRetDouble(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
    ): Double = 0.75

    override fun invokeStringNameIntRetInt(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      name: String,
      value: Int,
    ): Int {
      emittedSignal = name to value
      return 0
    }

    override fun invokeStringNameRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      value: String,
    ): GodotHandle {
      constructedClasses += value
      return GodotHandle.fromBackendToken(41L)
    }

    override fun invokeObjectBoolLongArgs(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      objectValue: GodotHandle,
      boolValue: Boolean,
      longValue: Long,
    ) {
      assertEquals(0L, longValue)
      addedChild = Triple(receiver.backendToken(), objectValue.backendToken(), boolValue)
    }

    override fun invokeObjectArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: GodotHandle?,
    ) {
      val call = receiver.backendToken() to value?.backendToken()
      if (descriptor.opcode == 14) removedChild = call.first to checkNotNull(call.second)
      else objectArguments += call
    }

    override fun invokeNodePathRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      path: String,
    ): GodotHandle? {
      assertEquals("Board", path)
      return GodotHandle.fromBackendToken(51L)
    }

    override fun invokeLongRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: Long,
    ): GodotHandle? {
      return when (descriptor.opcode) {
        18 -> {
          assertEquals(0L, value)
          GodotHandle.fromBackendToken(53L)
        }
        41 -> {
          assertEquals(10L, value)
          receiver
        }
        42 -> {
          assertEquals(1L, value)
          receiver
        }
        else -> error("Unexpected long-return-handle opcode=${descriptor.opcode}")
      }
    }

    override fun invokeLongArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: Long,
    ) {
      assertEquals(52, descriptor.opcode)
      longArgument = receiver.backendToken() to value
    }

    override fun invokeNoArgsRetHandle(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): GodotHandle? =
      GodotHandle.fromBackendToken(
        when (descriptor.opcode) {
          33 -> 31L
          36 -> 61L
          51 -> 71L
          else -> 52L
        }
      )

    override fun invokeObjectLongVector2Args(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      objectValue: GodotHandle?,
      longValue: Long,
      vectorValue: GodotVector2,
    ) {
      assertEquals(31L, objectValue?.backendToken())
      assertEquals(0L, longValue)
      assertEquals(GodotVector2(0.0f, 0.0f), vectorValue)
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
      assertEquals(53L, receiver.backendToken())
      assertEquals("tile_pressed", signal)
      assertEquals(17L, target.backendToken())
      assertEquals("_on_tile_pressed", method)
      assertEquals(0L, flags)
      return 0L
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
      assertEquals(53L, receiver.backendToken())
      assertEquals("finished", signal)
      assertEquals(53L, target.backendToken())
      assertEquals("_kanama_web_signal_dispatch0", method)
      assertEquals(71L, boundValue)
      assertEquals(4L, flags)
      return 0L
    }

    override fun invokeStringNameRetInt(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: String,
    ): Int {
      assertEquals(53L, receiver.backendToken())
      assertEquals("finished", value)
      return 0
    }

    override fun invokeStringNameRetBool(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: String,
    ): Boolean = value == "InputEventMouseButton"

    override fun invokeNoArgsRetBool(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): Boolean = if (descriptor.opcode == 44) particlesEmitting else descriptor.opcode == 24

    override fun invokeNoArgsRetDouble(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): Double {
      assertEquals(45, descriptor.opcode)
      return 1.0
    }

    override fun invokeNoArgsRetLong(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): Long = 1L

    override fun invokeStringNameArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: String,
    ) {
      assertEquals(47, descriptor.opcode)
      stringNameArgument = value
    }

    override fun invokeStringNameVector2iRetInt(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      name: String,
      value: GodotVector2i,
    ): Int {
      emittedVectorSignal = name to value
      return 0
    }

    override fun invokeNoArgsRetColor(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
    ): GodotColor = modulate

    override fun invokeColorArg(
      descriptor: GodotCallDescriptor,
      callSite: GodotCallSite,
      receiver: GodotHandle,
      value: GodotColor,
    ) {
      modulate = value
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
      assertEquals(61L, receiver.backendToken())
      assertEquals(17L, target.backendToken())
      assertEquals("position", property)
      assertEquals(GodotVector2(12.0f, 34.0f), finalValue)
      assertEquals(0.3, duration)
      return GodotHandle.fromBackendToken(62L)
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
      assertEquals(61L, receiver.backendToken())
      assertEquals(17L, target.backendToken())
      assertEquals("modulate", property)
      assertEquals(GodotColor(0.5f, 0.6f, 0.7f, 0.8f), finalValue)
      assertEquals(0.1, duration)
      return GodotHandle.fromBackendToken(63L)
    }
  }
}
